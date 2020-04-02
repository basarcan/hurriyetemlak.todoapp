package com.hurriyetemlak.todoapp.todoapp.service;

import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.exception.exceptions.EmailDoesNotValidException;
import com.hurriyetemlak.todoapp.todoapp.exception.exceptions.PasswordDoesNotExistException;
import com.hurriyetemlak.todoapp.todoapp.model.request.SignInRequest;
import com.hurriyetemlak.todoapp.todoapp.model.response.SignInResponse;
import com.hurriyetemlak.todoapp.todoapp.repository.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verifyNoInteractions;

@RunWith(MockitoJUnitRunner.class)
public class SignInServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    private SignInService signInService;

    @Mock
    private EncryptionService encryptionService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenService tokenService;

    @Test
    public void it_should_sign_in() throws PasswordDoesNotExistException {
        //given
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail("email@email.com");
        signInRequest.setPassword("password");

        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");

        given(encryptionService.encodeData(signInRequest.getPassword())).willReturn("encryptedPassword");
        given(userRepository.signIn(signInRequest.getEmail(),"encryptedPassword")).willReturn(user);
        given(tokenService.generateToken(user)).willReturn("token");

        //when
        SignInResponse response = signInService.signIn(signInRequest);

        //then
        assertThat(response.getFirstName()).isEqualTo("firstName");
        assertThat(response.getLastName()).isEqualTo("lastName");
        assertThat(response.getToken()).isEqualTo("token");
    }

    @Test
    public void it_should_return_null_when_user_mail_does_not_exist() throws PasswordDoesNotExistException {
        //given
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail("email@email.com");
        signInRequest.setPassword("password");


        given(encryptionService.encodeData(signInRequest.getPassword())).willReturn("encryptedPassword");
        given(userRepository.signIn(signInRequest.getEmail(),"encryptedPassword")).willReturn(null);

        //when
        SignInResponse response = signInService.signIn(signInRequest);

        //then
        verifyNoInteractions(tokenService);//girmeyeceğini bildiğim yerler
        assertThat(response).isNull();
    }

    @Test
    public void it_should_return_exception_when_user_password_incorrect() throws RuntimeException {

        //given
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail("email@email.com");
        signInRequest.setPassword("password");

        User user = new User();
        user.setEmail("email@email.com");
        user.setPassword(null);


        given(encryptionService.encodeData(signInRequest.getPassword())).willReturn("encryptedPassword");
        given(userRepository.signIn(signInRequest.getEmail(),"encryptedPassword")).willReturn(user);

        //when
        thrown.expect(PasswordDoesNotExistException.class);
        thrown.expectMessage("Girilen şifre yanlış");
        SignInResponse response = signInService.signIn(signInRequest);

        //then
         verifyNoInteractions(tokenService);//girmeyeceğini bildiğim yerler
    }

    @Test
    public void it_should_return_exception_when_user_typed_without_at_sign_email_adress()
    {
        //given
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail("emailemail.com");
        signInRequest.setPassword("password");

        //when
        thrown.expect(EmailDoesNotValidException.class);
        thrown.expectMessage("Girilen E-MAIL adresi hatalıdır.");
        SignInResponse signInResponse = signInService.signIn(signInRequest);

        //then
        verifyNoInteractions(userRepository);
        verifyNoInteractions(tokenService);//girmeyeceğini bildiğim yerler
    }

    @Test
    public void it_should_return_exception_when_user_typed_without_domain_email_adress()
    {
        //given
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setPassword("password");

        //when
        thrown.expect(EmailDoesNotValidException.class);
        thrown.expectMessage("Girilen E-MAIL adresi hatalıdır.");
        SignInResponse signInResponse = signInService.signIn(signInRequest);

        //then
        verifyNoInteractions(userRepository);
        verifyNoInteractions(tokenService);//girmeyeceğini bildiğim yerler
    }

}