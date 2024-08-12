package org.example.stylish.rowmapper;

import org.example.stylish.dto.UserResponseDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserResponseDto> {
    @Override
    public UserResponseDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(resultSet.getInt("id"));
        userResponseDto.setProvider(resultSet.getString("provider"));
        userResponseDto.setName(resultSet.getString("name"));
        userResponseDto.setEmail(resultSet.getString("email"));
        userResponseDto.setPicture(resultSet.getString("picture"));

        return userResponseDto;
    }
}
