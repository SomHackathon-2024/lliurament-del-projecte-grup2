package com.tecnocampus.hackathon.application;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tecnocampus.hackathon.application.dto.ActivityDTO;
import com.tecnocampus.hackathon.application.dto.PageDTO;
import com.tecnocampus.hackathon.application.dto.custom.ActivityFrontDTO;
import com.tecnocampus.hackathon.domain.Activity;
import com.tecnocampus.hackathon.exception.notfound.EntityNotFound;
import com.tecnocampus.hackathon.persistence.ActivityRepository;

@Service
public class ActivityService {
    private ActivityRepository activityRepository;
    private ModelMapper modelMapper;

    public ActivityService(ActivityRepository activityRepository, ModelMapper modelMapper) {
        this.activityRepository = activityRepository;
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

    // get most popular
}
