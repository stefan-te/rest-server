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

    public String createUser(User user) {
        log.info("Create user");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        int success = userRepository.createUser(user);
        if (success > 0) {
            Long userId = userRepository.getUserIdByLogin(user.getLogin());
            roleRepository.insertRole(userId);
        }
        return user.toString();
    }

    public String updateUser(String id, User user) {
        log.info("Update user");
        user.setId(Long.parseLong(id));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.updateUser(user);
        return user.toString();
    }

    public User getUser(String id) {
        log.info("Get user");
        return userRepository.getUserById(Long.valueOf(id));
    }

    public String deleteUser(String id) {
        log.info("Delete user");
        int res = userRepository.deleteUser(Long.valueOf(id));
        if (res == 0) {
            return "User was not deleted";
        } else {
            return "User deleted";
        }
    }
}
