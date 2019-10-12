package com.practice.feign.demo.client.controller;

import com.practice.feign.demo.client.dto.ResultObject;
import com.practice.feign.demo.client.dto.User;
import com.practice.feign.demo.client.dto.UserCreate;
import com.practice.feign.demo.client.feignclient.UserFeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserFeignClient userFeignClient;

    public UserController(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @RequestMapping("/createUser")
    public ResultObject<User> createUser(@RequestParam("username") String username,
                                         @RequestParam("password") String password,
                                         @RequestParam("verifyCode") String verifyCode) {
        UserCreate userCreate=new UserCreate(username,password);
        ResultObject<User> resultObject = userFeignClient.createUser(userCreate, verifyCode);
        User user = resultObject.getData();
        return new ResultObject<>(user);
    }

}
