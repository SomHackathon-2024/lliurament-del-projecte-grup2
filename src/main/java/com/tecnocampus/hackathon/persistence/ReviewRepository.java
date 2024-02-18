package com.tecnocampus.hackathon.persistence;

import com.tecnocampus.hackathon.domain.Review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, String>{
    public Page<Review> findAllByActivityId(String activityId, Pageable pageable);
}
