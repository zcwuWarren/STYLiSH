package org.example.stylish.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String provider;
    private String name;
    private String email;
    private String password;
    private String picture;
    private String accessToken;
}
