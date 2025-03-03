package com.company.marketplace_auth_service.dao;

import com.company.marketplace_auth_service.Utils.JwtUtils;
import com.company.marketplace_auth_service.exception.InvalidCredentialsException;
import com.company.marketplace_auth_service.exception.UserNotFoundException;
import com.company.marketplace_auth_service.model.UserDetailRequestModel;
import com.company.marketplace_auth_service.model.UserDetailResponseModel;
import com.company.marketplace_auth_service.repo.UserEntity;
import com.company.marketplace_auth_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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
        JwtUtils jwtUtils = new JwtUtils();

        String email = userDetailRequestModel.getEmail();
        String password = userDetailRequestModel.getPassword();
        logger.info(email + " " + password);
        List<UserEntity> result = userRepo.findByEmail(email);
        logger.info("Query result: " + result);
        logger.info("result" + result.toString());
        if (result.isEmpty()) {
            throw new UserNotFoundException("User with email " + email + "' not found");
        } else {
            UserEntity userEntity = result.get(0);
            if (password.equals(userEntity.getPassword_hash())) {
                String token=jwtUtils.generateToken(userEntity.getPassword_hash());
                System.out.println(token);
                userDetailResponseModel.setToken(token);
                userDetailResponseModel.setUsername(userEntity.getUsername());
                userDetailResponseModel.setEmail(userEntity.getEmail());
            } else {
                throw new InvalidCredentialsException("Invalid password for email" + email);
            }


        }
        return userDetailResponseModel;

    }
}
