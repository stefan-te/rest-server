package de.internetx.restserver.user;

import de.internetx.restserver.RestServerApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(RestServerApplication.class);

    private final UserRepositoryImpl userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepositoryImpl userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String createUser(User user) {
        log.info("Create user");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.createUser(user);
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
        return userRepository.getUser(Long.valueOf(id));
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
