package com.company.marketplace_auth_service.controller;

import com.company.marketplace_auth_service.Utils.JwtUtils;
import com.company.marketplace_auth_service.model.UserDetailRequestModel;
import com.company.marketplace_auth_service.model.UserDetailResponseModel;
import com.company.marketplace_auth_service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserService userService;

    // User Registration API
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDetailRequestModel userDetailRequestModel) {
        userService.registerUser(userDetailRequestModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetailResponseModel> loginUser(@RequestBody UserDetailRequestModel userDetailRequestModel) {
        logger.info("Login Request Received");
        logger.info("User Details: {}", userDetailRequestModel.toString());
        UserDetailResponseModel userDetailResponseModel =  userService.loginUser(userDetailRequestModel);
        return ResponseEntity.ok(userDetailResponseModel);
    }

    //Under development , facing invalid signing key issue
    @PostMapping("/refresh_token")
    public ResponseEntity<?> refreshAccessToken(@RequestBody String refreshToken) {
        try {

            if (refreshToken == null || refreshToken.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Refresh token is missing.");
            }

            JwtUtils jwtUtils = new JwtUtils();

            // Generate a new access token using the refresh token
            String newAccessToken = jwtUtils.generateAccessTokenFromRefreshToken(refreshToken);

            // Return the new access token in the response
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
        } catch (Exception e) {
            logger.error("Error while refreshing access token: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token.");
        }
    }

}
