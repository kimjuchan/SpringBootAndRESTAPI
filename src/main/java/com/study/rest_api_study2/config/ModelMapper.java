package com.study.rest_api_study2.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapper {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
