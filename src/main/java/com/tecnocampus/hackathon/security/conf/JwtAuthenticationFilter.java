package com.tecnocampus.hackathon.security.conf;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.tecnocampus.hackathon.domain.User;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private CustomUserDetailsService customUserDetailsService;
    private JwtTokenProvider jwtTokenProvider;
    private HandlerExceptionResolver resolver;

    @Value("${application.security.jwt.token-prefix}")
    private String tokenPrefix;

    public JwtAuthenticationFilter(CustomUserDetailsService customUserDetailsService, JwtTokenProvider jwtTokenProvider, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                filterChain.doFilter(request, response);
                return;
            }
            
            String jwt = getJwtFromAuthorizationHeader(request);
            if (jwt == null || jwt.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }
            
            String username = jwtTokenProvider.extractUsername(jwt);
            User userDetails = customUserDetailsService.loadUserByUsername(username);

            if (!jwtTokenProvider.isTokenValid(jwt, userDetails)) {
                filterChain.doFilter(request, response);
                return;
            }

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails.getAuthorities()
            );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            
            filterChain.doFilter(request, response);
        } catch(Exception e) {
            resolver.resolveException(request, response, null, e);
        }
    }

    private String getJwtFromAuthorizationHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken == null || !bearerToken.startsWith(tokenPrefix))
            return null;

        return bearerToken.replace(tokenPrefix, "");
    }
}
