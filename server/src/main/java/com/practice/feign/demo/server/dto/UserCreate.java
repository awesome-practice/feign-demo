package com.practice.feign.demo.server.dto;

import lombok.Data;

@Data
public class UserCreate {
    private String username;
    private String password;
}
