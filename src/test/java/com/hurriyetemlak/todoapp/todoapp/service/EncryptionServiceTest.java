package com.hurriyetemlak.todoapp.todoapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(MockitoJUnitRunner.class)
public class EncryptionServiceTest {

    @InjectMocks
    private EncryptionService encryptionService;


    @Test
    public void it_should_encrypt_base64_password()
    {
        //given

        //when
        String encryptedPass = encryptionService.encodeData("password");

        //then
        assertThat(encryptedPass).isEqualTo("cGFzc3dvcmQ=");
    }

}