package com.practice.feign.demo.server.controller;

import com.practice.feign.demo.server.dto.ResultObject;
import com.practice.feign.demo.server.dto.UserCreate;
import com.practice.feign.demo.server.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @PostMapping("/createUser")
    public ResultObject<User> createUser(@RequestBody UserCreate userCreate,@RequestParam("verifyCode") String verifyCode) {
        log.info("verified the code: " + verifyCode);
        log.info(userCreate.getUsername() + " created");
        String userId = "111";
        User user = new User(userId, userCreate.getPassword());
        return new ResultObject<>(user);
    }

}
