package com.rwws.rwserver.module.login.controller;

import com.rwws.rwserver.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.zalando.problem.Status;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class VerifyCodeControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetVerifyCode() throws Exception {
        var json = """
                {"email": "wublhappy@hotmail.com"}
                """;
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/verifycodes")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print());
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetVerifyCodeEmailInvalid() throws Exception {
        var json = """
                    {"email": "wublhappyhotmail.com"}
                """;
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/verifycodes")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().is(Status.BAD_REQUEST.getStatusCode()))
                .andDo(print());
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
