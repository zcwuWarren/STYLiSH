package org.example.stylish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipientDto {
    private String name;
    private String phone;
    private String email;
    private String address;
    private String time;
}
