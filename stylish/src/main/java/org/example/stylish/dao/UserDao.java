package org.example.stylish.dao;

import org.example.stylish.dto.FacebookUserProfileDto;
import org.example.stylish.dto.UserDto;
import org.example.stylish.dto.UserResponseDto;

public interface UserDao {

    UserResponseDto createAccount(UserDto userDto);

    UserResponseDto login(UserDto userDto);

    UserResponseDto insertOrUpadateAccount(FacebookUserProfileDto facebookUserProfileDto);
}
