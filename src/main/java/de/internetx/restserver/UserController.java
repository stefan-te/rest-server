package de.internetx.restserver;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public String createUser() {
        return "POST create user";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable String id) {
        return "PUT update user";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable String id) {
        return "GET get user";
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        return "DELETE get user";
    }
}
