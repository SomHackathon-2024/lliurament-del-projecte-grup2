package com.tecnocampus.hackathon.api;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.tecnocampus.hackathon.application.ActivityService;
import com.tecnocampus.hackathon.application.dto.PageDTO;
import com.tecnocampus.hackathon.application.dto.ReservationDTO;
import com.tecnocampus.hackathon.application.dto.custom.ActivityFrontDTO;
import com.tecnocampus.hackathon.application.dto.ActivityDTO;



@RestController
@RequestMapping("/api/activity")
@Validated
public class ActivityRestController {
    private ActivityService activityService;

    public ActivityRestController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping()
    public ActivityDTO createActivity(@Validated @RequestBody ActivityDTO activityDTO) {
        return activityService.create(activityDTO);
    }

    @GetMapping()
    public PageDTO<ActivityFrontDTO> getActivities(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Principal principal) {
        return activityService.getMultiple(page, size);
    }

    @GetMapping("/upcoming")
    public PageDTO<ActivityFrontDTO> getUpcoming(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return activityService.getUpcoming(page, size);
    }

    @GetMapping("/past")
    public PageDTO<ActivityFrontDTO> getPast(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return activityService.getPast(page, size);
    }

    @GetMapping("/range")
    public PageDTO<ActivityFrontDTO> getRange(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam String start, @RequestParam String end) {
        return activityService.getBetween(page, size, start, end);
    }

    @PostMapping("/{activityId}/attend")
    public ReservationDTO postMethodName(@PathVariable String activityId, Principal principal) {
        return activityService.attend(activityId, principal.getName());
    }
    

    @GetMapping("/{activityId}")
    public ActivityDTO getActivity(@PathVariable String activityId) {
        return activityService.get(activityId);
    }


}