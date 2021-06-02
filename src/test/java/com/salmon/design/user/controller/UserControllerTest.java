package com.salmon.design.user.controller;

import org.apache.catalina.loader.WebappClassLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @date 2021-6-2 - 17:50
 * Created by Salmon
 */
@SpringBootTest
class UserControllerTest {

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext wac;

    @Test
    void get_information() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/user/get_information.do");
        try {
            String response = mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString();
            System.out.println(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}