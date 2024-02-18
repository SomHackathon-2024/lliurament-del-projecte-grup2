package com.tecnocampus.hackathon.api.rateLimiting;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterConfig {

    @Bean
    public FilterRegistrationBean<RateLimitingFilter> rateLimiterFilter(RateLimiterService rateLimiterService) {
        FilterRegistrationBean<RateLimitingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RateLimitingFilter(rateLimiterService));
        registrationBean.addUrlPatterns("/api/*"); // Apply on specific URL patterns
        return registrationBean;
    }
}