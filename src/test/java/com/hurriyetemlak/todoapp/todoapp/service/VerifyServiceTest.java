package com.hurriyetemlak.todoapp.todoapp.service;

import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class VerifyServiceTest {
    @InjectMocks
    private VerifyService verifyService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void it_should_verify() {
        //given
        User user = new User();
        user.setToken("token");
        user.setId("userId");
        given(userRepository.verifyToken("token")).willReturn(user);

        //when
        String response = verifyService.verify("token");

        //then
        assertThat(response).isEqualTo("userId");
    }

    @Test
    public void it_should_not_verify() {
        //given
        given(userRepository.verifyToken("token")).willReturn(null);

        //when
        String response = verifyService.verify("token");

        //then
        assertThat(response).isEqualTo("");
    }

}