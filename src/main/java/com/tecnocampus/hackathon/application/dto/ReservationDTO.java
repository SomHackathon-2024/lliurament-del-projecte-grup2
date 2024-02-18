package com.tecnocampus.hackathon.application.dto;

import com.tecnocampus.hackathon.domain.Activity;
import com.tecnocampus.hackathon.domain.User;

import lombok.Data;

@Data
public class ReservationDTO {
    private String id;
    private String user;
    private String activity;

    public void setUser(User user) {
        this.user = user.getId();
    }

    public void setActivity(Activity activity) {
        this.activity = activity.getId();
    }
}
