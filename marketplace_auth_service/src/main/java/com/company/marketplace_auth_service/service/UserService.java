package com.company.marketplace_auth_service.service;

import com.company.marketplace_auth_service.model.UserDetailRequestModel;
import com.company.marketplace_auth_service.model.UserDetailResponseModel;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public void registerUser(UserDetailRequestModel userDetailRequestModel);

    public UserDetailResponseModel loginUser(UserDetailRequestModel userDetailRequestModel);


}
