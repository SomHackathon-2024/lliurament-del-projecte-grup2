package com.tecnocampus.hackathon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Configuration
public class ModelDTOMapper {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // modelMapper.createTypeMap(Review.class, ReviewDTO.class)
        //     .addMappings(mapper -> {
        //         mapper.map(src -> src.getEnrollment().getUser().getName(), ReviewDTO::setAuthor);
        //     });


        modelMapper.validate();

        return modelMapper;
    }
}