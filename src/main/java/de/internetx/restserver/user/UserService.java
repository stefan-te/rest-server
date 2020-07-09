package de.internetx.restserver.user;

import de.internetx.restserver.RestServerApplication;
import de.internetx.restserver.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(RestServerApplication.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String createUser(UserModel userModel) {
        log.info("Create user");
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        int success = userRepository.createUser(userModel);
        if (success > 0) {
            Long userId = userRepository.getUserIdByLogin(userModel.getLogin());
            roleRepository.insertRole(userId);
        }
        return userModel.toString();
    }

    public String updateUser(Long id, UserModel userModel) {
        log.info("Update user");
        userModel.setId(id);
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        userRepository.updateUser(userModel);
        return userModel.toString();
    }

    public UserModel getUser(Long id) {
        log.info("Get user");
        return userRepository.getUserById(id);
    }

    public String deleteUser(Long id) {
        log.info("Delete user");
        int res = userRepository.deleteUser(id);
        if (res == 0) {
            return "User was not deleted";
        } else {
            return "User deleted";
        }
    }
}
