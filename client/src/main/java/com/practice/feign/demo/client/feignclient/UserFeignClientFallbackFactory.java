package com.practice.feign.demo.client.feignclient;

import com.practice.feign.demo.client.dto.ResultObject;
import com.practice.feign.demo.client.dto.User;
import com.practice.feign.demo.client.dto.UserCreate;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(Throwable throwable) {
        log.error("fallback of createUser", throwable);

        return new UserFeignClient() {
            @Override
            public ResultObject<User> createUser(UserCreate userCreate, String verifyCode) {
                return new ResultObject<>("600", "createUser fallback");
            }
        };
    }

}
