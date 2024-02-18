package com.tecnocampus.hackathon.persistence;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecnocampus.hackathon.domain.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {
    public void deleteByIdAndCreatorUsername(String activityId, String username);

    Page<Activity> findByDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
    Page<Activity> findByDateGreaterThan(LocalDateTime date, Pageable pageable);
    Page<Activity> findByDateLessThanEqual(LocalDateTime date, Pageable pageable);
}