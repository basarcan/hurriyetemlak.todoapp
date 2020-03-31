package com.hurriyetemlak.todoapp.todoapp.controller;

import com.hurriyetemlak.todoapp.todoapp.service.SignUpService;
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
@WebMvcTest(controllers = SignUpController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc _mMockMvc;
    private MockMvc mockMvc;

    @MockBean
    private SignUpService _mSignUpService;

    @Test
    public void it_should_sign_up() throws Exception {
        //given

        //when
        ResultActions resultActions = _mMockMvc.perform(post("/sign-up").contentType("application/json").content("{\n" +
                "  \"name\":\"firstName\",\n" +
                "  \"lastName\": \"lastName\",\n" +
                "  \"email\": \"email@email.com\",\n" +
                "  \"password\": \"password\"\n" +
                "}"));

        //then
        resultActions.andExpect(status().isCreated());
    }


}