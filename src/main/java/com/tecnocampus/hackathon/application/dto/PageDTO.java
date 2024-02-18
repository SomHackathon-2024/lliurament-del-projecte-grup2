package com.tecnocampus.hackathon.application.dto;

import org.springframework.data.domain.Page;
import java.util.List;
import lombok.Data;

@Data
public class PageDTO<T> {
    List<T> content;
    
    long totalItems;

    public PageDTO(Page<T> page) {
        this.content = page.getContent();
        this.totalItems = page.getTotalElements();
    }
}
