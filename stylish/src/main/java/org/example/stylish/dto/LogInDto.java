package org.example.stylish.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogInDto {
    private String provider;
    private String email;
    private String password;
    @JsonProperty("access_token")
    private String accessToken;
}
