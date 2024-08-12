package org.example.stylish.service.serviceImpl;

import org.example.stylish.dto.FacebookUserProfileDto;
import org.example.stylish.service.FacebookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FacebookServiceImpl implements FacebookService {

    @Value("${facebook.graphApiUrl}")
    private String graphApiUrl;

    @Value("${facebook.fields}")
    private String fields;

    @Override
    public FacebookUserProfileDto getUserProfile(String facebookToken) {
        // getUserProfile (facebook token) -> parse facebook token and return as FacebookUserProfileDto
        // parse facebook token and get the user profile (name, email, picture)
        String url = String.format("%s?fields=%s&access_token=%s", graphApiUrl, fields, facebookToken);
//        System.out.println(facebookToken);
//        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        FacebookUserProfileDto facebookUserProfileDto = restTemplate.getForObject(url, FacebookUserProfileDto.class);
//        System.out.println(facebookUserProfileDto.getEmail());
        return facebookUserProfileDto;
    }
}
