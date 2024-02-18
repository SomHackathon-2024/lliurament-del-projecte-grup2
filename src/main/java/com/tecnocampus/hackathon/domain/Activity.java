package com.tecnocampus.hackathon.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.tecnocampus.hackathon.domain.enums.EItemStatus;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Data
public class Activity {
    @Id
    private String id = TSID.fast().toString();

    private String title;
    private String desc;
    private String bodyContent; // base64 html

    private List<String> thumbnailPhotos; // main photo
    private List<String> photos; // list of files displayed in the main page

    private double latitude;
    private double longitude;

    private LocalDateTime date;
    private BigDecimal price; // can be 0
    private int maxAttendees; // by default is 20;

    // private List<Reservation> reservations;

    private EItemStatus status = EItemStatus.OK; // admins could take it down

    // private List<Review> reviews; 

    private List<String> extraFiles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User creator;

    @Column(updatable = false)
    private LocalDateTime dateOfPublication = LocalDateTime.now();
}