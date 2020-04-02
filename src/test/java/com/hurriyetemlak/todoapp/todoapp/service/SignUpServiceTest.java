package com.hurriyetemlak.todoapp.todoapp.service;

import com.hurriyetemlak.todoapp.todoapp.converter.SignUpConverter;
import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.model.request.SignUpRequestModel;
import com.hurriyetemlak.todoapp.todoapp.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SignUpServiceTest {
    @InjectMocks
    private SignUpService signUpService;
    @Mock
    private SignUpConverter signUpConverter;
    @Mock
    private UserRepository userRepository;

    @Test
    public void it_should_sign_up() {
        //given
        SignUpRequestModel signUpRequestModel = new SignUpRequestModel();
        signUpRequestModel.setEmail("email@email.com");
        signUpRequestModel.setPassword("password");
        signUpRequestModel.setFirstName("firstName");
        signUpRequestModel.setLastName("lastName");

        User user = new User();

        given(signUpConverter.convert(signUpRequestModel)).willReturn(user);

        //when
        signUpService.signUp(signUpRequestModel);

        //then
        verify(userRepository).save(user);
    }
}