package com.company.marketplace_auth_service.dao;

import com.company.marketplace_auth_service.model.UserDetailRequestModel;
import com.company.marketplace_auth_service.model.UserDetailResponseModel;
import com.company.marketplace_auth_service.repo.UserEntity;
import com.company.marketplace_auth_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class UserDaoImpl implements UserDao {

    Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

    @Autowired
    UserRepo userRepo;


    @Override
    public void registerUser(UserDetailRequestModel userDetailRequestModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDetailRequestModel.getUsername());
        userEntity.setEmail(userDetailRequestModel.getEmail());
        userEntity.setPassword_hash(userDetailRequestModel.getPassword());

        userRepo.save(userEntity);
    }

    @Override
    public UserDetailResponseModel loginUser(UserDetailRequestModel userDetailRequestModel) {
        UserDetailResponseModel userDetailResponseModel = new UserDetailResponseModel();

        //Testing purpose
        userDetailResponseModel.setEmail("amitpatle199@gmail");
        userDetailResponseModel.setUsername("amitpatle199");
        userDetailResponseModel.setAccessToken(null);
        userDetailResponseModel.setRefreshToken(null);

        return userDetailResponseModel;
    }
}
