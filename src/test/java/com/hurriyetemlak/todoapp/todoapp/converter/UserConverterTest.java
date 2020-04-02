package com.hurriyetemlak.todoapp.todoapp.converter;

import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.model.request.SignUpRequestModel;
import com.hurriyetemlak.todoapp.todoapp.service.EncryptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {
    @InjectMocks
    private SignUpConverter signUpConverter;

    @Mock
    private EncryptionService encryptionService;

    @Test
    public void it_should_convert_sign_up_request_to_sign_up_domain()
    {
        //given
        SignUpRequestModel signUpRequestModel = new SignUpRequestModel();
        signUpRequestModel.setEmail("email@email.com");
        signUpRequestModel.setFirstName("firstName");
        signUpRequestModel.setLastName("lastName");
        signUpRequestModel.setPassword("password");

        given(encryptionService.encodeData("password")).willReturn("encryptedPassword");
        //signUpRequestModel.setPassword(encryptionService.encryptData());

        //when
        User response = signUpConverter.convert(signUpRequestModel);

        //then
        assertThat(response.getEmail()).isEqualTo("email@email.com");
        assertThat(response.getFirstName()).isEqualTo("firstName");
        assertThat(response.getId()).isNull();
        assertThat(response.getLastName()).isEqualTo("lastName");
        assertThat(response.getPassword()).isEqualTo("encryptedPassword");
    }

}