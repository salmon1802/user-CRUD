package com.salmon.design.user.service.impl;

import com.salmon.design.user.common.Const;
import com.salmon.design.user.common.ResponseCode;
import com.salmon.design.user.common.ServerResponse;
import com.salmon.design.user.pojo.User;
import com.salmon.design.user.service.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @date 2021-6-2 - 15:49
 * Created by Salmon
 */
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private IUserService userService;


    @BeforeEach
    void setUp() {
        System.out.println("BeforeEach");
    }

    @AfterEach
    void tearDown() {
        System.out.println("AfterEach");

    }

    @Test
    void login() {
        ServerResponse<User> response = this.userService.login("salmon", "lhh123321");
        if(response.isSuccess()){
            System.out.println(response.getMsg());
        }

    }

    @Test
    void register() {
        User user = new User();
        user.setUid(1);
        user.setUsername("章一鸣");
        user.setPassword("123");
        ServerResponse<String> response = this.userService.register(user);
        System.out.println(response.getMsg());
    }


    @Test
    void updateInformation() {
        User user = new User();
        user.setUid(1);
        user.setUsername("张三");
        user.setIp("127.0.0.1");
        user.setNumber(1);
        user.setPhone("13635541122");

        ServerResponse<User> response = this.userService.updateInformation(user);
        System.out.println(response.getMsg());
    }

    @Test
    void getInformation() {
        ServerResponse<User> response = this.userService.getInformation(1);
        System.out.println(response.getData());
    }

    @Test
    void delete() {
        ServerResponse<String> response = this.userService.delete(1);
        System.out.println(response.getMsg());
    }
}