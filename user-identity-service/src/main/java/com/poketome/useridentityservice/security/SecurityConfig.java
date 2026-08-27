package com.poketome.useridentityservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 1. Disable CSRF (Cross-Site Request Forgery) protection
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Disable server-side sessions (Make the application entirely Stateless)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Define the routing rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll() // Public
                        .requestMatchers("/h2-console/**").permitAll() // Allow access to local DB viewer
                        .anyRequest().authenticated() // Everything else requires a valid JWT
                )

                // 4. Fix H2 console framing issues (H2 uses iframes, which Spring blocks by
                // default)
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))

                // 5. Insert our custom JWT filter BEFORE the default Spring authentication
                // filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }
}