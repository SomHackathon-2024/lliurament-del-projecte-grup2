package com.tecnocampus.hackathon.domain;

import java.time.LocalDateTime;
import java.util.Set;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class FileRecord {
    @Id
    private String id = TSID.fast().toString();

    private String name;
    private String path;

    private String type;
    private long size;

    @Column(updatable = false)
    private LocalDateTime dateOfPublication = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User uploader;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "file_user", 
        joinColumns = @JoinColumn(name = "file_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> whitelist = new java.util.HashSet<>();

    private boolean isPublic;
}
