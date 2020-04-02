package com.hurriyetemlak.todoapp.todoapp.controller;

import com.hurriyetemlak.todoapp.todoapp.service.VerifyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VerifyController.class)
public class VerifyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VerifyService verifyService;

    @Test
    public void it_should_verify() throws Exception {
        //given

        given(verifyService.verify("token")).willReturn("userId");

        //when
        ResultActions response = mockMvc.perform(get("/verify?token=token"));

        //then
        response.andExpect(status().isOk());
        verify(verifyService).verify("token");
    }
}