package com.tecnocampus.hackathon.application.dto;
import com.tecnocampus.hackathon.domain.Language;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LanguageDTO {
    @NotEmpty(message = "Language code cannot be empty")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Must only contain letters")
    @Size(min = 2, max = 10, message = "Language code must be between 2 and 10 chars.")
    private String name;

    public LanguageDTO(Language language){
        name=language.getName();
    }
}
