package org.example.stylish.service;

import org.example.stylish.dto.LogInDto;
import org.example.stylish.dto.SignUpDto;

import java.util.Map;

public interface UserService {
    Map<String, Object> register(SignUpDto signUpDto);

    Map<String, Object> login(LogInDto logInDto);

    Map<String, Object> getProfile(String token);

    Map<String, Object> facebookSignIn(String facebookToken);
}
