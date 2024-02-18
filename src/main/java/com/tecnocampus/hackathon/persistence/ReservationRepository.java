package com.tecnocampus.hackathon.persistence;

import com.tecnocampus.hackathon.domain.Activity;
import com.tecnocampus.hackathon.domain.Reservation;
import com.tecnocampus.hackathon.domain.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    public void deleteByUserAndActivity(User user, Activity activity);
    public Optional<Reservation> findByUserUsernameAndActivityId(String username, String activityId);

    @Query("SELECT r FROM Reservation r JOIN r.activity a WHERE a.date > CURRENT_TIMESTAMP AND r.user.username = :username")
    public Page<Reservation> findUpcomingReservationsByUsername(@Param("username") String username, Pageable pageable);

    public List<Reservation> findAllByUserUsername(String username);
}
