package de.internetx.restserver.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String createUser(@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody UserModel userModel) {
        return userService.updateUser(id, userModel);
    }

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN') or authentication.principal.id.equals(#id)")
    public UserModel getUser(@PathVariable(value = "id") Long id) {
        return userService.getUser(id);
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
