package com.hurriyetemlak.todoapp.todoapp.service;

import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.model.request.SignInRequest;
import com.hurriyetemlak.todoapp.todoapp.model.response.SignInResponse;
import com.hurriyetemlak.todoapp.todoapp.repository.SignInRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SignInServiceTest {

    @InjectMocks
    private SignInService signInService;

    @Mock
    private EncryptionService encryptionService;

    @Mock
    private SignInRepository signInRepository;

    @Mock
    private TokenService tokenService;

    @Test
    public void it_should_sign_in()
    {
        //given
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail("email@email.com");
        signInRequest.setPassword("password");

        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");

        given(encryptionService.encodeData(signInRequest.getPassword())).willReturn("encyptedPassword");
        given(signInRepository.search(signInRequest.getEmail(),"encyptedPassword")).willReturn(user);
        given(tokenService.generateToken(user)).willReturn("token");

        //when
        SignInResponse response = signInService.signIn(signInRequest);

        //then
        assertThat(response.getFirstName()).isEqualTo("firstName");
        assertThat(response.getLastName()).isEqualTo("lastName");
        assertThat(response.getToken()).isEqualTo("token");
    }

}