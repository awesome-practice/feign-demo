package com.practice.feign.demo.client.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String userId;

    private String username;

    public User() {
    }

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
