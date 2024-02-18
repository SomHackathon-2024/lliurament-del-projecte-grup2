package com.tecnocampus.hackathon.security.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import com.tecnocampus.hackathon.application.dto.UserDTO;
import com.tecnocampus.hackathon.domain.User;
import com.tecnocampus.hackathon.exception.DuplicateException;
import com.tecnocampus.hackathon.persistence.UserRepository;
import com.tecnocampus.hackathon.security.conf.CustomUserDetailsService;
import com.tecnocampus.hackathon.security.conf.JwtTokenProvider;

@Service
public class AuthenticationService {
    private final JwtTokenProvider JwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationService(JwtTokenProvider JwtTokenProvider, AuthenticationManager authenticationManager,
            CustomUserDetailsService customUserDetailsService, UserRepository userRepository) {
        this.JwtTokenProvider = JwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        
        this.userRepository = userRepository;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        User userDetails = this.customUserDetailsService.loadUserByUsername(request.getUsername());

        var jwtToken = JwtTokenProvider.generateToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse();
        
        response.setId(userDetails.getId());
        response.setUsername(userDetails.getUsername());

        response.setAccessToken(jwtToken);

        return response;
    }

    public AuthenticationResponse register(UserDTO request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new DuplicateException("User already exists with username: " + request.getUsername());

        if (userRepository.existsByPhone(request.getUsername()))
            throw new DuplicateException("User already exists with phone number: " + request.getPhone());

        User userDetails = new User();
        userDetails.setName(request.getName());
        userDetails.setPhone(request.getPhone());

        userDetails.setUsername(request.getUsername());
        userDetails.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(userDetails);

        var jwtToken = JwtTokenProvider.generateToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse();
        
        response.setId(userDetails.getId());
        response.setUsername(userDetails.getUsername());

        response.setAccessToken(jwtToken);

        return response;
    }
}