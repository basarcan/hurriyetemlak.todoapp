package com.hurriyetemlak.todoapp.todoapp.service;

import com.hurriyetemlak.todoapp.todoapp.domain.User;
import com.hurriyetemlak.todoapp.todoapp.exception.exceptions.EmailDoesNotValidException;
import com.hurriyetemlak.todoapp.todoapp.exception.exceptions.PasswordDoesNotExistException;
import com.hurriyetemlak.todoapp.todoapp.model.request.SignInRequest;
import com.hurriyetemlak.todoapp.todoapp.model.response.SignInResponse;
import com.hurriyetemlak.todoapp.todoapp.repository.SignInRepository;
import org.springframework.stereotype.Service;

import static com.hurriyetemlak.todoapp.todoapp.utils.EmailValidation.isValidEmail;

@Service
public class SignInService {

    private final EncryptionService encryptionService;
    private final SignInRepository signInRepository;
    private final TokenService tokenService;

    public SignInService(EncryptionService encryptionService, SignInRepository signInRepository, TokenService tokenService) {
        this.encryptionService = encryptionService;
        this.signInRepository = signInRepository;
        this.tokenService = tokenService;
    }


    public SignInResponse signIn(SignInRequest signInRequest) throws RuntimeException {
        if (!isValidEmail(signInRequest.getEmail())) {
            throw new EmailDoesNotValidException("Girilen E-MAIL adresi hatalıdır.");
        }

        String encryptedPassword = encryptionService.encodeData(signInRequest.getPassword());
        User user = signInRepository.search(signInRequest.getEmail(), encryptedPassword);
        if (user == null) {
            return null;
        } else if (user.getEmail() != null && user.getPassword() == null) {
            throw new PasswordDoesNotExistException("Girilen şifre yanlış");
        }
        String token = tokenService.generateToken(user);
        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setFirstName(user.getFirstName());
        signInResponse.setLastName(user.getLastName());
        signInResponse.setToken(token);

        return signInResponse;
    }
}
