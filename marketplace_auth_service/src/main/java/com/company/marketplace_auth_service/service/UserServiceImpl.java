package com.company.marketplace_auth_service.service;

import com.company.marketplace_auth_service.Utils.JwtUtils;
import com.company.marketplace_auth_service.dao.UserDao;
import com.company.marketplace_auth_service.model.UserDetailRequestModel;
import com.company.marketplace_auth_service.model.UserDetailResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;
    
    JwtUtils jwtUtils = new JwtUtils();

    @Override
    public void registerUser(UserDetailRequestModel userDetailRequestModel) {
        userDao.registerUser(userDetailRequestModel);

    }

    @Override
    public UserDetailResponseModel loginUser(UserDetailRequestModel userDetailRequestModel) {
        UserDetailResponseModel userDetailResponseModel = new UserDetailResponseModel();

        // Fetch the user details from the database via DAO
        userDetailResponseModel = userDao.loginUser(userDetailRequestModel);

        // Generate Access Token and Refresh Token using JwtUtils
        String accessToken = jwtUtils.generateAccessToken(userDetailRequestModel.getEmail());
        String refreshToken = jwtUtils.generateRefreshToken(userDetailRequestModel.getEmail());

        // Set tokens in the UserDetailResponseModel
        userDetailResponseModel.setAccessToken(accessToken);
        userDetailResponseModel.setRefreshToken(refreshToken);

        // Return the response model containing user details and tokens
        return userDetailResponseModel;
    }



}
