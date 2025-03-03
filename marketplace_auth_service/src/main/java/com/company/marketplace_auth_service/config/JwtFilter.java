package com.company.marketplace_auth_service.config;

import com.company.marketplace_auth_service.Utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public JwtFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Extract Authorization header
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extract JWT token from the header
            String jwtToken = authorizationHeader.substring(7); // Remove "Bearer " prefix

            try {
                // Extract username from the token using JwtUtil
                String username = jwtUtils.extractUsername(jwtToken);

                // Ensure the user is not already authenticated
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    if (jwtUtils.isTokenValid(jwtToken)) {
                        // Create Authentication Token (you can add roles/authorities here)
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                username, null, null);
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        // Set the authentication into SecurityContext
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (Exception e) {
                logger.error("JWT Authentication failed: {}");
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
