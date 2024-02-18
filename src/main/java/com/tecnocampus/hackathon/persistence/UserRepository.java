package com.tecnocampus.hackathon.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecnocampus.hackathon.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByPhone(String phone);
    boolean existsByUsername(String username);
    
    Optional<User> findByUsername(String username);
    
    Page<User> findAll(Pageable pageable);
    
}