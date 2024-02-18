package com.tecnocampus.hackathon.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ActivityDTO {
    private String id;

    @NotEmpty(message="Title cannot be empty")
    @Size(min=3, max=60, message="Title must be between 5 and 50 characters")
    private String title;

    @Size(min=3, max=250, message="Description must be between 5 and 250 characters")
    @NotEmpty(message="Description cannot be empty")
    private String description;

    @NotEmpty(message="Description cannot be empty")
    private String bodyContent;

    @NotNull(message="Latitude cannot be empty")
    private Double latitude;

    @NotNull(message="Longitude cannot be empty")
    private Double longitude;

    @NotNull(message="Date cannot be empty")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    private int maxAttendees = 20;

    private List<String> thumbnailPhotos;
    private List<String> photos;

    private List<String> extraFiles;
}