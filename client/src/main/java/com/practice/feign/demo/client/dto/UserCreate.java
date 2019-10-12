package com.practice.feign.demo.client.dto;

import lombok.Data;

@Data
public class UserCreate {
    private String username;
    private String password;

    public UserCreate() {

    }

    public UserCreate(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
