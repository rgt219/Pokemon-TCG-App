package com.poketome.useridentityservice.security;

import com.poketome.useridentityservice.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Extract the Authorization header from the incoming HTTP request
        String authHeader = request.getHeader("Authorization");

        // 2. Check if the header is missing or doesn't start with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Pass it down the chain (it will likely be rejected later)
            return;
        }

        // 3. Extract the token and parse the username
        String token = authHeader.substring(7);
        try {
            String username = jwtService.extractUsername(token);

            // 4. If the token is valid and the user isn't already authenticated in this
            // thread...
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // 5. Create an official Spring Security "ID Card" for this user
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        username, null, Collections.emptyList());

                // 6. Slide the ID Card into the Security Context (The application's memory for
                // this specific request)
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (Exception e) {
            // If the token is expired or fake, we just do nothing and let the request
            // continue as "unauthenticated"
        }

        // 7. Pass the request to the next filter in the chain
        filterChain.doFilter(request, response);
    }
}