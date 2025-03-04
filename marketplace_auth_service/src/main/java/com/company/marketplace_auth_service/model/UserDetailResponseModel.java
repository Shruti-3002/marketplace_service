package com.company.marketplace_auth_service.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailResponseModel {
    private String username;
    private String email;
    private String accessToken;
    private String refreshToken;
}
