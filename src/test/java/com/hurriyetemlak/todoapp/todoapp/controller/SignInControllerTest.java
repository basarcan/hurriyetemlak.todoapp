package com.hurriyetemlak.todoapp.todoapp.controller;

import com.hurriyetemlak.todoapp.todoapp.service.SignInService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SignInController.class)
public class SignInControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SignInService signInService;

    @Test
    public void it_should_sign_in() throws Exception {
        //given

        //when
        ResultActions resultActions = mockMvc.perform(post("/sign-in").contentType("application/json").content("{\n" +
                "  \"email\": \"email@email.com\",\n" +
                "  \"password\": \"password\"\n" +
                "}"));

        //then
        resultActions.andExpect(status().isAccepted());
    }

}