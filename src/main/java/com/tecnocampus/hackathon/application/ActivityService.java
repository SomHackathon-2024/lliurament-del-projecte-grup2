package com.tecnocampus.hackathon.application;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tecnocampus.hackathon.application.dto.ActivityDTO;
import com.tecnocampus.hackathon.application.dto.PageDTO;
import com.tecnocampus.hackathon.application.dto.ReservationDTO;
import com.tecnocampus.hackathon.application.dto.ReviewDTO;
import com.tecnocampus.hackathon.application.dto.custom.ActivityFrontDTO;
import com.tecnocampus.hackathon.domain.Activity;
import com.tecnocampus.hackathon.domain.Reservation;
import com.tecnocampus.hackathon.domain.Review;
import com.tecnocampus.hackathon.domain.User;
import com.tecnocampus.hackathon.exception.notfound.EntityNotFound;
import com.tecnocampus.hackathon.persistence.ActivityRepository;
import com.tecnocampus.hackathon.persistence.ReservationRepository;
import com.tecnocampus.hackathon.persistence.ReviewRepository;
import com.tecnocampus.hackathon.persistence.UserRepository;

@Service
public class ActivityService {
    private ActivityRepository activityRepository;
    private UserRepository userRepository;
    private ReservationRepository reservationRepository;
    private ReviewRepository reviewRepository;

    private ModelMapper modelMapper;

    public ActivityService(ActivityRepository activityRepository, UserRepository userRepository, ReservationRepository reservationRepository, ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    public ActivityDTO get(String activityId) {
        Activity activity = activityRepository.findById(activityId)
            .orElseThrow( () -> new EntityNotFound(Activity.class, activityId) );

        return modelMapper.map(activity, ActivityDTO.class);
    }

    public PageDTO<ActivityFrontDTO> getMultiple(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        
        return new PageDTO<ActivityFrontDTO>(activityRepository.findAll(pageable)
            .map(activity -> modelMapper.map(activity, ActivityFrontDTO.class)));
    }

    public ActivityDTO create(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        activity.setTitle(activityDTO.getTitle());
        activity.setDesc(activityDTO.getDescription());
        activity.setDate(activityDTO.getDate());

        activity.setMaxAttendees(activityDTO.getMaxAttendees());
    
        activity.setLongitude(activityDTO.getLongitude());
        activity.setLatitude(activityDTO.getLatitude());

        activityRepository.save(activity);

        return modelMapper.map(activity, ActivityDTO.class);
    }

    public void delete(String activityId, String username) {
        activityRepository.deleteByIdAndCreatorUsername(activityId, username);
    }

    public PageDTO<ActivityFrontDTO> getUpcoming(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        LocalDateTime today = LocalDateTime.now();

        return new PageDTO<ActivityFrontDTO>(activityRepository.findByDateGreaterThan(today, pageable)
            .map(activity -> modelMapper.map(activity, ActivityFrontDTO.class)));
    }

    public PageDTO<ActivityFrontDTO> getPast(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        LocalDateTime today = LocalDateTime.now();

        return new PageDTO<ActivityFrontDTO>(activityRepository.findByDateLessThanEqual(today, pageable)
            .map(activity -> modelMapper.map(activity, ActivityFrontDTO.class)));
    }

    public PageDTO<ActivityFrontDTO> getBetween(int page, int size, String start, String end) {
        Pageable pageable = PageRequest.of(page, size);
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);

        return new PageDTO<ActivityFrontDTO>(activityRepository.findByDateBetween(startDate, endDate, pageable)
            .map(activity -> modelMapper.map(activity, ActivityFrontDTO.class)));
    }

    public ReservationDTO attend(String activityId, String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow( () -> new EntityNotFound(User.class, username) );

        Activity activity = activityRepository.findById(activityId)
            .orElseThrow( () -> new EntityNotFound(Activity.class, activityId) );

        if(reservationRepository.countByActivityId(activityId) >= activity.getMaxAttendees())
            throw new IllegalStateException("Activity is full");

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setActivity(activity);
        
        reservationRepository.save(reservation);

        return modelMapper.map(reservation, ReservationDTO.class);
    }

    public ResponseEntity<Void> cancelAttendance(String activityId, String username) {
        Reservation reservation = reservationRepository.findByUserUsernameAndActivityId(username, activityId)
            .orElseThrow( () -> new EntityNotFound(Reservation.class, username+"+"+activityId) );

        reservationRepository.deleteById(reservation.getId());

        return ResponseEntity.noContent().build();
    }

    public PageDTO<ReviewDTO> getReviews(String activityId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return new PageDTO<ReviewDTO>(reviewRepository.findAllByActivityId(activityId, pageable)
            .map(reservation -> modelMapper.map(reservation, ReviewDTO.class)));
    }

    public ReviewDTO createReview(String activityId, ReviewDTO reviewDTO, String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow( () -> new EntityNotFound(User.class, username) );

        Activity activity = activityRepository.findById(activityId)
            .orElseThrow( () -> new EntityNotFound(Activity.class, activityId) );

        Review review = new Review();
        review.setUser(user);
        review.setActivity(activity);

        review.setRating(reviewDTO.getRating());
        review.setTitle(reviewDTO.getTitle());
        review.setContents(reviewDTO.getContents());

        reviewRepository.save(review);

        return modelMapper.map(review, ReviewDTO.class);
    }
}