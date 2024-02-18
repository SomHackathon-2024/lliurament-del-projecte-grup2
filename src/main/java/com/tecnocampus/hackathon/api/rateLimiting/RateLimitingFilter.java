package com.tecnocampus.hackathon.api.rateLimiting;


import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RateLimitingFilter implements Filter {

    private final RateLimiterService rateLimiterService;

    public RateLimitingFilter(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        String userId = extractUserId(request);

        // if (!rateLimiterService.tryAcquire(userId)) {
        //     HttpServletResponse httpResponse = (HttpServletResponse) response;

        //     httpResponse.setStatus(429);
        //     httpResponse.setContentType("application/json");
        //     httpResponse.setCharacterEncoding("UTF-8");
        //     httpResponse.getWriter().write("{\"message\": \"Please wait before making another request\"}");
        //     return;
        // }

        chain.doFilter(request, response);
    }

    private String extractUserId(ServletRequest request) {
        // user ip address
        return ((HttpServletRequest) request).getRemoteAddr();
    }

}