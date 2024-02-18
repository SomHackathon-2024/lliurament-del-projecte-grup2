package com.tecnocampus.hackathon.domain;

import com.tecnocampus.hackathon.application.dto.LanguageDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Language {
    @Id
    private String name;

    public Language(LanguageDTO languageDTO){
        name=languageDTO.getName();
    }
}