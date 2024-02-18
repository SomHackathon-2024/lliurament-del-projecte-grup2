package com.tecnocampus.hackathon.domain;

import java.time.LocalDateTime;

import com.tecnocampus.hackathon.application.dto.ReviewDTO;

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

    @OneToOne(fetch = FetchType.LAZY)
    private Reservation enrollment;

    private String title;
    private String contents;

    @Column(name = "satisfaction_degree")
    private int satisfactionDegree;

    private LocalDateTime creationDate = LocalDateTime.now();

    public Review(ReviewDTO reviewDTO) {
        this.title = reviewDTO.getTitle();
        this.contents = reviewDTO.getContents();
        this.satisfactionDegree = reviewDTO.getSatisfactionDegree();
    }
}
