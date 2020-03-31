package com.hurriyetemlak.todoapp.todoapp.controller;

import com.hurriyetemlak.todoapp.todoapp.model.request.SignInRequest;
import com.hurriyetemlak.todoapp.todoapp.model.response.SignInResponse;
import com.hurriyetemlak.todoapp.todoapp.service.SignInService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
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
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail("email@email.com");
        signInRequest.setPassword("password");

        SignInResponse signInResponse = new SignInResponse();
        given(signInService.signIn(signInRequest)).willReturn(signInResponse);

        //when
        ResultActions resultActions = mockMvc.perform(post("/sign-in").content("{\n" +
                "  \"email\" : \"email@email.com\",\n" +
                "  \"password\" : \"password\"\n" +
                "}")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isOk());
        verify(signInService).signIn(any(SignInRequest.class));
    }
}