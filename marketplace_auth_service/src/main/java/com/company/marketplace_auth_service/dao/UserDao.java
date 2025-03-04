package com.company.marketplace_auth_service.dao;

import com.company.marketplace_auth_service.model.UserDetailRequestModel;
import com.company.marketplace_auth_service.model.UserDetailResponseModel;
import org.springframework.http.ResponseEntity;

public interface UserDao {
    void registerUser(UserDetailRequestModel userDetailRequestModel);
    UserDetailResponseModel loginUser(UserDetailRequestModel userDetailRequestModel);

}
