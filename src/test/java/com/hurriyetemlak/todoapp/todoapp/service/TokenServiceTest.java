package com.hurriyetemlak.todoapp.todoapp.service;

import com.hurriyetemlak.todoapp.todoapp.domain.User;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TokenServiceTest {
    @InjectMocks
    private TokenService tokenService;

    @Mock
    private JwtTokenGeneratorService jwtTokenGeneratorService;

    @Test
    public void it_should_generate_token() {
        //given
        User user = new User();
        user.setLastName("lastName");
        user.setFirstName("firstName");
        user.setPassword("password");
        user.setId(0L);
        user.setEmail("email@email.com");

        given(jwtTokenGeneratorService.generateToken(any(HashMap.class), any(DateTime.class))).willReturn("token");


        //when
        String response = tokenService.generateToken(user);

        //then
        assertThat(response).isEqualTo("token");

    }

}