package com.hurriyetemlak.todoapp.todoapp.service;

import com.hurriyetemlak.todoapp.todoapp.converter.SignUpConverter;
import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.model.SignUpRequestModel;
import com.hurriyetemlak.todoapp.todoapp.repository.SignUpRepository;

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
public class UserServiceTest {

    @InjectMocks
    private SignUpService signUpService;

    @Mock
    private SignUpConverter signUpConverter;

    @Mock
    private SignUpRepository signUpRepository;

    @Test
    public void it_should_sign_up()
    {
        //given
        SignUpRequestModel signUpRequestModel = new SignUpRequestModel();
        signUpRequestModel.setFirstName("firstName");
        signUpRequestModel.setLastName("lastName");
        signUpRequestModel.setEmail("email@email.com");
        signUpRequestModel.setPassword("password");

        User user = new User();
        user.setId(0L);
        user.setEmail("email@email.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPassword("encryptedPassword");

        given(signUpConverter.convert(signUpRequestModel)).willReturn(user);

        //when
        signUpService.signUp(signUpRequestModel);

        //then
        ArgumentCaptor<User> signUpArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(signUpRepository).save(signUpArgumentCaptor.capture());
        User userArgumentCaptorValue = signUpArgumentCaptor.getValue();
        assertThat(userArgumentCaptorValue.getEmail()).isEqualTo("email@email.com");
        assertThat(userArgumentCaptorValue.getFirstName()).isEqualTo("firstName");
        assertThat(userArgumentCaptorValue.getId()).isEqualTo(0L);
        assertThat(userArgumentCaptorValue.getLastName()).isEqualTo("lastName");
        assertThat(userArgumentCaptorValue.getPassword()).isEqualTo("encryptedPassword");
    }


}