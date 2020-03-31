package com.hurriyetemlak.todoapp.todoapp.converter;

import com.hurriyetemlak.todoapp.todoapp.domain.SignIn;
import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.model.SignInRequestModel;
import com.hurriyetemlak.todoapp.todoapp.model.SignInResponseModel;
import com.hurriyetemlak.todoapp.todoapp.service.EncryptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SignInConverterTest {

    @InjectMocks
    private SignInConverter signInConverter;

    @Mock
    private EncryptionService encryptionService;

    @Test
    public void it_should_convert_sign_in_request_to_sign_in_domain()
    {
        //given
        SignInRequestModel signInRequestModel = new SignInRequestModel();
        signInRequestModel.setEmail("email@email.com");
        signInRequestModel.setPassword("password");

        given(encryptionService.encodeData("password")).willReturn("encryptedPassword");
        //signUpRequestModel.setPassword(encryptionService.encryptData());

        //when
        SignInResponseModel response = signInConverter.convert(new User());

        //then
        assertThat(response.getEmail()).isEqualTo("email@email.com");
        assertThat(response.getToken()).isEqualTo("token");
    }

}