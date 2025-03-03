package com.company.marketplace_auth_service.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailResponseModel {
    private String username;
    private String email;
    private String token;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
