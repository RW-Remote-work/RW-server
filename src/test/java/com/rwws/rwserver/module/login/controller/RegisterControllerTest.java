package com.rwws.rwserver.module.login.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rwws.rwserver.BaseTest;
import com.rwws.rwserver.controller.response.RegisterResponse;
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
    @Autowired
    private ObjectMapper objectMapper;

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
        RegisterResponse registerResponse = objectMapper.readValue(responseBody, RegisterResponse.class);
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
