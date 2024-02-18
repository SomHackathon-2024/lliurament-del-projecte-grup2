package com.tecnocampus.hackathon.application.dto;

import com.tecnocampus.hackathon.domain.enums.ERole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleDTO {
    private String id;
    private ERole name;
}
