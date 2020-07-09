package de.internetx.restserver.user;

import de.internetx.restserver.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createUser(@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOP', 'ROLE_REGISTRY', 'ROLE_SQL') or authentication.principal.id.equals(#id)")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserModel userModel) {
        return userService.updateUser(id, userModel);
    }

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOP', 'ROLE_REGISTRY', 'ROLE_SQL') or authentication.principal.id.equals(#id)")
    public ResponseEntity<UserModel> getUser(@PathVariable(value = "id") Long id) {
        return userService.getUser(id);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
