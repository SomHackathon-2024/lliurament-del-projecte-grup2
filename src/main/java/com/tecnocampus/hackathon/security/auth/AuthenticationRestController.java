package com.tecnocampus.hackathon.security.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tecnocampus.hackathon.application.dto.UserDTO;

@RestController
@Validated
public class AuthenticationRestController {

    private final AuthenticationService service;

    @Value("${application.security.jwt.token-prefix}")
    private String tokenPrefix;

    public AuthenticationRestController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/api/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = service.authenticate(request);
        // sending the token in the header and the body
        return ResponseEntity.ok()
                .header("Authorization", tokenPrefix + response.getAccessToken())
                .body(service.authenticate(request));
    }

    @PostMapping("/api/register")
    public ResponseEntity<AuthenticationResponse> register(@Validated @RequestBody UserDTO request) {
        AuthenticationResponse response = service.register(request);
    
        // sending the token in the header and the body
        return ResponseEntity.ok()
            .header("Authorization", tokenPrefix + response.getAccessToken())
            .body(response);
    }

}
