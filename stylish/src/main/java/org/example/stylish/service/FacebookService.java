package org.example.stylish.service;

import org.example.stylish.dto.FacebookUserProfileDto;

public interface FacebookService {
    FacebookUserProfileDto getUserProfile(String facebookToken);
}
