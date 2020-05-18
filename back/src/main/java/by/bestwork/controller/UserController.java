package by.bestwork.controller;

import by.bestwork.domain.User;
import by.bestwork.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-api")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public User getUserById(@RequestParam("id") Long id) {
        return service.loadUserById(id);
    }
}
