package com.tecnocampus.hackathon.security.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {

    private String id;
    private String username;

    @JsonProperty("access_token")
    private String accessToken;
}