package com.rwws.rwserver.module.login.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.rwws.rwserver.BaseTest;
import com.rwws.rwserver.module.login.domain.response.LoginResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class LoginControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {
        String json = """
                {
                    "email": "wublhappy@hotmail.com",
                    "password": "123456!a"
                }
                """;
        MvcResult mvcResult = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/sessions")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        LoginResponse loginResponse = JSONUtil.toBean(responseBody, LoginResponse.class);
        Assertions.assertTrue(StrUtil.isNotEmpty(loginResponse.getToken()));
    }

    @Test
    public void testLoginFail() throws Exception {
        String json = """
                {
                    "email": "wublhappy@hotmail.com",
                    "password": "123456!aaa"
                }
                """;
        MvcResult mvcResult = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/sessions")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        LoginResponse loginResponse = JSONUtil.toBean(responseBody, LoginResponse.class);
        Assertions.assertTrue(StrUtil.isNotEmpty(loginResponse.getToken()));
    }
}
