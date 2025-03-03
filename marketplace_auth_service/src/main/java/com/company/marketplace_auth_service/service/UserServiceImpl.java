package com.company.marketplace_auth_service.service;

import com.company.marketplace_auth_service.dao.UserDao;
import com.company.marketplace_auth_service.model.UserDetailRequestModel;
import com.company.marketplace_auth_service.model.UserDetailResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;


    @Override
    public void registerUser(UserDetailRequestModel userDetailRequestModel) {
        userDao.registerUser(userDetailRequestModel);

    }
    @Override
    public UserDetailResponseModel loginUser(UserDetailRequestModel userDetailRequestModel) {
        return userDao.loginUser(userDetailRequestModel);
    }


}
