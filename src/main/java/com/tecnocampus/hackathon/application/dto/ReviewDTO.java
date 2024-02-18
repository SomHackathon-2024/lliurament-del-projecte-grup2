package com.tecnocampus.hackathon.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tecnocampus.hackathon.validation.ICreateReview;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO {

    private String id;

    @NotEmpty(message = "Title cannot be empty", groups = {ICreateReview.class})
    private String title;

    @NotEmpty(message = "Contents cannot be empty", groups = {ICreateReview.class})
    private String contents;

    @NotNull(message = "Satisfaction degree cannot be empty", groups = {ICreateReview.class})
    @Min(value = 1, message = "Satisfaction degree must be greater than 0", groups = {ICreateReview.class})
    @Max(value = 5, message = "Satisfaction degree must be less than 6", groups = {ICreateReview.class})
    private int satisfactionDegree;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String creationDate;

    private String author;
}
