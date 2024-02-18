package com.tecnocampus.hackathon.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnocampus.hackathon.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String name);
}