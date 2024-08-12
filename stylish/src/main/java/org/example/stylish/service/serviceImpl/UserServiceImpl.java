package org.example.stylish.service.serviceImpl;

import io.jsonwebtoken.Claims;
import org.example.stylish.dao.daoimpl.UserDaoImpl;
import org.example.stylish.dto.*;
import org.example.stylish.exception.InputEmptyBlankException;
import org.example.stylish.exception.ProviderInvalidException;
import org.example.stylish.service.FacebookService;
import org.example.stylish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private FacebookService facebookService;

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Override
    // combine UserResponseDto and TokenDto in service layer
    public Map<String, Object> register(SignUpDto signUpDto) {
        // for insert into database
        // id auto_increment, provider default "native", picture default "empty"
        // transfer signUpDto to useDto
        if (signUpDto.getName() == null || signUpDto.getName().isBlank() || signUpDto.getEmail() == null || signUpDto.getEmail().isBlank()) {
            throw new InputEmptyBlankException("Input invalid: Null, Empty or Blank");
        }
        UserDto userDto = new UserDto();
        userDto.setName(signUpDto.getName());
        userDto.setEmail(signUpDto.getEmail());
        // hashed signUpDto and transfer to userDto
        userDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        // to insert userDto data (from signUpDto and hashed password) into DB and return userResponseDto
        UserResponseDto userResponseDto = userDao.createAccount(userDto);

        // create tokenDto instance to call the createToken method
        TokenDto tokenDto = jwtTokenProvider.createToken(userResponseDto);

        // combine UserResponseDto and TokenDto
        // use LinkedHashMap to keep insertion order
        Map<String, Object> respone = new LinkedHashMap<>();
        respone.put("access_token", tokenDto.getToken());
        respone.put("access_expired", tokenDto.getExpiredTime());
        respone.put("user", userResponseDto);
        return respone;
    }

    @Override
    public Map<String, Object> login(LogInDto logInDto) {
        // exception for empty and blank input
        if (logInDto.getEmail() == null || logInDto.getPassword() == null || logInDto.getEmail().isBlank() || logInDto.getPassword().isBlank()) {
            throw new InputEmptyBlankException("Input invalid: Null, Empty or Blank");
        }
        // exception for invalid provider
        if (!(logInDto.getProvider().equals("native") || logInDto.getProvider().equals("facebook"))) {
            throw new ProviderInvalidException("Provider invalid");
        }
        // get email, password data from LoginDto
        UserDto userDto = new UserDto();
        userDto.setProvider(logInDto.getProvider());
        userDto.setEmail(logInDto.getEmail());
        // hash the login password to compare with database
        userDto.setPassword(logInDto.getPassword());
        // required if provider is facebook
        userDto.setAccessToken(logInDto.getAccessToken());

        // search and compare hashed password by email
        UserResponseDto userResponseDto = userDao.login(userDto);
        // use userResponseDto mapped from database to create payload in token
        TokenDto tokenDto = jwtTokenProvider.createToken(userResponseDto);

        Map<String, Object> respone = new LinkedHashMap<>();
        respone.put("access_token", tokenDto.getToken());
        respone.put("access_expired", tokenDto.getExpiredTime());
        respone.put("user", userResponseDto);
        return respone;
    }

    @Override
    public Map<String, Object> getProfile(String token) {
        // check prefix of Bearer
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        jwtTokenProvider.validateToken(token);
        Claims claims = jwtTokenProvider.parseToken(token);

        ProfileDto profileDto = new ProfileDto();
        profileDto.setProvider(claims.get("provider", String.class));
        profileDto.setName(claims.get("name", String.class));
        profileDto.setEmail(claims.get("email", String.class));
        profileDto.setPicture(claims.get("picture", String.class));

        Map<String, Object> respone = new LinkedHashMap<>();
        respone.put("user", profileDto);

        return respone;
    }

    @Override
    public Map<String, Object> facebookSignIn(String facebookToken) {


// controller (facebook token) -> userService.facebookSignIn
// FacebookSignIn (facebook token) -> facebookService.getUserProfile return FacebookUserProfile

        // parse facebookToken and return as facebookUserProfileDto
        FacebookUserProfileDto facebookUserProfileDto = facebookService.getUserProfile(facebookToken);
//        System.out.println("userService.facebookService :" + facebookToken); //OK
//        System.out.println("userService.facebookService :" + facebookUserProfileDto.getEmail()); //OK

        // pass facebookUserProfileDto to insert or update database depend on existences of account, and return the mapped userResponseDto
        UserResponseDto userResponseDto = userDaoImpl.insertOrUpadateAccount(facebookUserProfileDto);
//        System.out.println("userService.insertOrUpadateAccount :" + userResponseDto.getPicture()); // OK

        // pass userResponseDto to generate JWT token
        TokenDto tokenDto = jwtTokenProvider.createToken(userResponseDto);
//        System.out.println("userService.createToken :" + userResponseDto.getName()); // OK


        Map<String, Object> respone = new LinkedHashMap<>();
        respone.put("access_token", tokenDto.getToken());
        respone.put("access_expired", tokenDto.getExpiredTime());
        respone.put("user", userResponseDto);

        return respone;
    }
}

