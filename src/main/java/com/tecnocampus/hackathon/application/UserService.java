package com.tecnocampus.hackathon.application;


import com.tecnocampus.hackathon.exception.notfound.EntityNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tecnocampus.hackathon.application.dto.UserDTO;
import com.tecnocampus.hackathon.domain.User;
import com.tecnocampus.hackathon.persistence.UserRepository;


@Service
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDTO getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFound(User.class, userId));

        return modelMapper.map(user, UserDTO.class);
    }
}