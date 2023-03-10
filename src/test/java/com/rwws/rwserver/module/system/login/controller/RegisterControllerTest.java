package com.rwws.rwserver.module.system.login.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.rwws.rwserver.BaseTest;
import com.rwws.rwserver.module.system.login.domain.response.RegisterResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class RegisterControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegister() throws Exception {
        var json = """
                {
                    "email": "wublhappy@hotmail.com", 
                    "code": "161589", 
                    "password": "123456!a"
                }
                """;
        MvcResult mvcResult = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/members")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        RegisterResponse registerResponse = JSONUtil.toBean(responseBody, RegisterResponse.class);
        Assertions.assertTrue(StrUtil.isNotEmpty(registerResponse.getToken()));
    }

    @Test
    public void testRegisterFail() throws Exception {
        var json = """
                {
                    "email": "wublhappy@hotmail.com", 
                    "code": "776438", 
                    "password": "123456!a"
                }
                """;
        MvcResult mvcResult = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/members")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print())
                .andReturn();
    }

}
