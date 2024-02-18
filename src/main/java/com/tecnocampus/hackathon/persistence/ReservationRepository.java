package com.tecnocampus.hackathon.persistence;

import com.tecnocampus.hackathon.domain.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

}
