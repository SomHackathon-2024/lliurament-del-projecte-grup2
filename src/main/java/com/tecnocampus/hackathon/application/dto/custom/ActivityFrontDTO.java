package com.tecnocampus.hackathon.application.dto.custom;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ActivityFrontDTO {
    private String id;

    private String title;

    private List<String> thumbnailPhotos;
}
