package com.tecnocampus.hackathon.persistence;

import com.tecnocampus.hackathon.domain.Review;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, String>{

}
