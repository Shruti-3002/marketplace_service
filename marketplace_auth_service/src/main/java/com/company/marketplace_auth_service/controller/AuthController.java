package com.company.marketplace_auth_service.controller;

import com.company.marketplace_auth_service.Utils.JwtUtils;
import com.company.marketplace_auth_service.model.UserDetailRequestModel;
import com.company.marketplace_auth_service.model.UserDetailResponseModel;
import com.company.marketplace_auth_service.service.UserService;
import com.company.marketplace_auth_service.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;


    // User Registration API
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDetailRequestModel userDetailRequestModel) {
        userService.registerUser(userDetailRequestModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("sucess");
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetailResponseModel> loginUser( @Validated @RequestBody UserDetailRequestModel userDetailRequestModel) {
       UserDetailResponseModel userDetailResponseModel =  userService.loginUser(userDetailRequestModel);
       return ResponseEntity.ok(userDetailResponseModel);
    }
}
