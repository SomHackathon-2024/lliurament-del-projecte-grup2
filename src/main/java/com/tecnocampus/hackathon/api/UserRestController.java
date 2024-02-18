package com.tecnocampus.hackathon.api;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.tecnocampus.hackathon.application.UserService;
import com.tecnocampus.hackathon.application.dto.UserDTO;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserRestController {
    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }
}