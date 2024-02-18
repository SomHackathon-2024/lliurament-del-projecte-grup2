package com.tecnocampus.hackathon.api;


import java.util.List;

import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.tecnocampus.hackathon.application.UserService;
import com.tecnocampus.hackathon.application.dto.ReservationDTO;

@RestController
@RequestMapping("/api/user")
@Validated
public class UserRestController {
    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }
}