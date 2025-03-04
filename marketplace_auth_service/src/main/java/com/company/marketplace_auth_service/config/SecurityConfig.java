package com.company.marketplace_auth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login", "/auth/register", "/auth/refresh_token").permitAll() // Allow access to /auth/login and /auth/register
                        .anyRequest().authenticated() // Require authentication for other endpoints
                )
                .formLogin(AbstractHttpConfigurer::disable) // Disable default login page
                .httpBasic(AbstractHttpConfigurer::disable); // Disable HTTP Basic Auth

        return http.build();

    }
}