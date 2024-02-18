package com.tecnocampus.hackathon.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


import io.hypersistence.tsid.TSID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@Data
public class Activity {
    @Id
    private String id = TSID.fast().toString();

    private String title;
    private String desc;

    @Column(name = "body_content")
    private String bodyContent; // base64 html

    @ManyToMany
    @JoinTable(name = "activity_files", joinColumns = @JoinColumn(name = "activity_id"), inverseJoinColumns = @JoinColumn(name = "file_id"))
    private List<FileRecord> thumbnailPhotos; // main photo

    @ManyToMany
    @JoinTable(name = "activity_files", joinColumns = @JoinColumn(name = "activity_id"), inverseJoinColumns = @JoinColumn(name = "file_id"))
    private List<FileRecord> photos; // list of files displayed in the main page

    private double latitude;
    private double longitude;

    private LocalDateTime date;

    @Column(name = "max_attendees")
    private int maxAttendees; // by default is 20;

    // private List<Reservation> reservations;

    // private List<Review> reviews; 

    @ManyToMany
    @JoinTable(name = "activity_files", joinColumns = @JoinColumn(name = "activity_id"), inverseJoinColumns = @JoinColumn(name = "file_id"))
    private List<FileRecord> extraFiles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User creator;

    @Column(name="date_of_publication", updatable = false)
    private LocalDateTime dateOfPublication = LocalDateTime.now();
}