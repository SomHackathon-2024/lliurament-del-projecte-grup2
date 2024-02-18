package com.tecnocampus.hackathon.application.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class FileRecordDTO {
    private String id;
    
    private String name;
    private String type;
    private long size;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfPublication;
}
