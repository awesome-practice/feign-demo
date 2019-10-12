package com.practice.feign.demo.client.feignclient;

import com.practice.feign.demo.client.dto.ResultObject;
import com.practice.feign.demo.client.dto.User;
import com.practice.feign.demo.client.dto.UserCreate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("feign-demo-server")
public interface UserFeignClient {

    @PostMapping("/createUser")
    ResultObject<User> createUser(@RequestBody UserCreate userCreate, @RequestParam("verifyCode") String verifyCode);
}
