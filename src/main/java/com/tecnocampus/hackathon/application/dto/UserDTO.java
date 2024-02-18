package com.tecnocampus.hackathon.application.dto;

import java.util.Set;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private String id;

    @Pattern(regexp = "^[a-zA-Z0-9]{3,}$", message = "Username must be at least 3 characters long and contain only letters and numbers")
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Phone cannot be empty")
    private String phone;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    private Set<String> roles;
}