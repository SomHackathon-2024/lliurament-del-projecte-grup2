package com.tecnocampus.hackathon.domain;

import java.time.LocalDateTime;


import io.hypersistence.tsid.TSID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_activity_review")
public class Review {
    @Id
    private String id = TSID.fast().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Activity activity;
    
    private String title;
    private String contents;

    private int rating;

    private LocalDateTime creationDate = LocalDateTime.now();
}