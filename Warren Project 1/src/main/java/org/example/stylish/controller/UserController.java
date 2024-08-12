package org.example.stylish.controller;

import org.example.stylish.dto.LogInDto;
import org.example.stylish.dto.SignUpDto;
import org.example.stylish.exception.InvalidTokenException;
import org.example.stylish.exception.ProviderInvalidException;
import org.example.stylish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@CrossOrigin
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private final String EMAIL_REGEX = "^(.+)@(\\S+)$";

    @PostMapping("/api/1.0/user/signup")
    // use SignUpDTO to handle JSON
    public ResponseEntity<?> register(@RequestBody SignUpDto signUpDto) {
        // validate email format
        if (!Pattern.matches(EMAIL_REGEX, signUpDto.getEmail())) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid email format");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        Map<String, Object> response = userService.register(signUpDto);

        // initialize another map to put in response to meet the spec
        Map<String, Object> result = new HashMap<>();
        result.put("data", response);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/1.0/user/signin")
    public ResponseEntity<?> login(@RequestBody LogInDto logInDto) {
        // determine provider and call the specific method
        if (logInDto.getProvider().equals("native")) {
            // validate email format
            if (!Pattern.matches(EMAIL_REGEX, logInDto.getEmail())) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Invalid email format");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

            Map<String, Object> response = userService.login(logInDto);

            Map<String, Object> result = new HashMap<>();
            result.put("data", response);

            return ResponseEntity.ok(result);
        } else if (logInDto.getProvider().equals("facebook")) {

//            System.out.println(logInDto.getAccessToken());
            Map<String, Object> response = userService.facebookSignIn(logInDto.getAccessToken());

            Map<String, Object> result = new HashMap<>();
            result.put("data", response);
            return ResponseEntity.ok(result);
        } else {
            throw new ProviderInvalidException("Provider invalid");
        }
    }


    @GetMapping("/api/1.0/user/profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String token) throws InvalidTokenException {

        Map<String, Object> response = userService.getProfile(token);

        Map<String, Object> result = new HashMap<>();
        result.put("data", response);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/1.0/user/facebook_login")
    public String facbookLogin() {
        return "facebookLogin";
    }
}
