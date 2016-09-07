package com.cdk.finaltest.dto;

import java.io.Serializable;

/**
 * Created by bagades on 9/6/2016.
 */
public class LoginInputDto implements Serializable{
    private static final long serialVersionUID = 1L;
    private String email;
    private String password;

    public LoginInputDto() {
    }

    public LoginInputDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "LoginInputDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
