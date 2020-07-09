package de.internetx.restserver.user;

import de.internetx.restserver.RestServerApplication;
import de.internetx.restserver.models.RoleRepository;
import de.internetx.restserver.models.UserModel;
import de.internetx.restserver.models.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<String> createUser(UserModel userModel) {
        log.info("Create user");
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        int success = userRepository.createUser(userModel);
        if (success > 0) {
            Long userId = userRepository.getUserIdByLogin(userModel.getLogin());
            roleRepository.insertRole(userId);
            return new ResponseEntity<>("User " + userModel.getLogin() + " created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User could not be created", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateUser(Long id, UserModel userModel) {
        log.info("Update user");
        userModel.setId(id);
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        int success = userRepository.updateUser(userModel);
        if (success > 0) {
            return new ResponseEntity<>("User " + userModel.getLogin() + " updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("User could not be updated", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<UserModel> getUser(Long id) {
        log.info("Get user");
        UserModel userModel = userRepository.getUserById(id);
        if (userModel != null) {
            return new ResponseEntity<>(userModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteUser(Long id) {
        log.info("Delete user");
        int success = userRepository.deleteUser(id);
        if (success > 0) {
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("User could not be deleted", HttpStatus.BAD_REQUEST);
    }
}
