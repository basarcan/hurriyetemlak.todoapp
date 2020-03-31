package com.hurriyetemlak.todoapp.todoapp.service;

import com.hurriyetemlak.todoapp.todoapp.converter.SignInConverter;
import com.hurriyetemlak.todoapp.todoapp.domain.SignIn;
import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.model.SignInRequestModel;
import com.hurriyetemlak.todoapp.todoapp.model.SignInResponseModel;
import com.hurriyetemlak.todoapp.todoapp.repository.SignInRepository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SignInServiceTest
{
    @InjectMocks
    private SignInService signInService;

    @Mock
    private SignInConverter signInConverter;

    @Mock
    private SignInRepository signInRepository;

    @Test
    public void it_should_sign_in()
    {
        //given
        SignInRequestModel signInRequestModel = new SignInRequestModel();
        signInRequestModel.setEmail("email@email.com");
        signInRequestModel.setPassword("password");

        User user = new User();
        user.setId(0L);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email@email.com");
        user.setPassword("password");

        SignInResponseModel signInResponseModel = new SignInResponseModel();
        signInResponseModel.setToken("token");
        signInResponseModel.setEmail("email@email.com");


        given(signInRepository.get(signInRequestModel)).willReturn(user);
        given(signInConverter.convert(user)).willReturn(signInResponseModel);

        //when
        SignInResponseModel signInResponse = signInService.signIn(signInRequestModel);

        //then
        assertThat(signInResponse.getToken()).isEqualTo("token");
        assertThat(signInResponse.getEmail()).isEqualTo("email@email.com");

    }
}