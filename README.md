# demo步骤
1. 启动eureka, `com.practice.feign.demo.eureka.EurekaApplication`
1. 启动server, `com.practice.feign.demo.server.ServerApplication`
1. 启动client, `com.practice.feign.demo.client.ClientApplication`
1. 执行测试 `curl 'http://localhost:8082/createUser?username=john&password=1212&verifyCode=akak'`

# feign使用介绍
## feign是什么
feign是HTTP调用框架, 供调用者调用别人的http endpoint. 它的用法与spring mvc相近.
## feign用法
### 目标
client调用server提供的endpoint "/createUser" 
### server
server暴露http endpoint如下:
```java
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
```
### client
1. 加入feign的库
    ```xml
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-openfeign</artifactId>
            </dependency>
            <dependency>
                <groupId>io.github.openfeign</groupId>
                <artifactId>feign-httpclient</artifactId>
            </dependency>
    ```
1. enable feign:  通过注解`@EnableFeignClients`
    ```java
    @EnableFeignClients
    @SpringBootApplication
    public class ClientApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(ClientApplication.class, args);
        }
    
    }
    ```
1. 定义feign client, 在@FeignClient指定被调用服务名"feign-demo-server", 接口方法签名与被调用方的controller方法签名一致
    ```java
    @FeignClient("feign-demo-server")
    public interface UserFeignClient {
    
        @PostMapping("/createUser")
        ResultObject<User> createUser(@RequestBody UserCreate userCreate, @RequestParam("verifyCode") String verifyCode);
    }
    
    ```
 1. 指定createUser熔断时的返回
    ```java
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
    
    ```
1. feign client 至此已定义完. 可启动来执行测试