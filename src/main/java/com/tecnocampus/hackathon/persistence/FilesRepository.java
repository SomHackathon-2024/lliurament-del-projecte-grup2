package com.tecnocampus.hackathon.persistence;

import com.tecnocampus.hackathon.domain.FileRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FilesRepository extends JpaRepository<FileRecord, String> {

}