package de.internetx.restserver;

import de.internetx.restserver.auth.UserRepositoryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepositoryImpl userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepositoryImpl userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.createUser(user);
        return user.toString();
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable String id, @RequestBody User user) {
        user.setId(Long.parseLong(id));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.updateUser(user);
        return user.toString();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userRepository.getUser(Long.valueOf(id));
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        int res = userRepository.deleteUser(Long.valueOf(id));
        if (res == 0) {
            return "User was not deleted";
        } else {
            return "User deleted";
        }
    }
}
