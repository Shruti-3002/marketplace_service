package com.company.marketplace_auth_service.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UserDetailRequestModel {

    //It can be null because we require it only for register purpose
    String username;

    @NonNull
    String email;

    @NonNull
    String password;
}
