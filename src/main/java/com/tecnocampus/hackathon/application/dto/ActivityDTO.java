package com.tecnocampus.hackathon.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ActivityDTO {
    private String id;

    private String title;
    private String description;
    private String bodyContent;

    private double latitude;
    private double longitude;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    private BigDecimal price;
    private int maxAttendees = 20;
}