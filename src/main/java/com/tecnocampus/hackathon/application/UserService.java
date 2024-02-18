package com.tecnocampus.hackathon.application;


import com.tecnocampus.hackathon.exception.notfound.EntityNotFound;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tecnocampus.hackathon.application.dto.PageDTO;
import com.tecnocampus.hackathon.application.dto.ReservationDTO;
import com.tecnocampus.hackathon.application.dto.UserDTO;
import com.tecnocampus.hackathon.domain.User;
import com.tecnocampus.hackathon.persistence.ReservationRepository;
import com.tecnocampus.hackathon.persistence.UserRepository;


@Service
public class UserService {
    private UserRepository userRepository;
    private ReservationRepository reservationRepository;

    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
    }

    public UserDTO getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFound(User.class, userId));

        return modelMapper.map(user, UserDTO.class);
    }

    public PageDTO<ReservationDTO> getUpcomingReservations(int page, int size, String username) {
        if(!userRepository.existsByUsername(username))
            throw new EntityNotFound(User.class, username);

        Pageable pageable = PageRequest.of(page, size);
        return new PageDTO<ReservationDTO>(reservationRepository.findUpcomingReservationsByUsername(username, pageable)
            .map(activity -> modelMapper.map(activity, ReservationDTO.class)));
    }

    public List<ReservationDTO> getAllReservations(String username) {
        if(!userRepository.existsByUsername(username))
            throw new EntityNotFound(User.class, username);

        return reservationRepository.findAllByUserUsername(username)
            .stream()
            .map(reservation -> modelMapper.map(reservation, ReservationDTO.class))
            .collect(Collectors.toList());
    }
}