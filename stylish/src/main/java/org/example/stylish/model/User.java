package org.example.stylish.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// simply plain object to map from database
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String provider; // default "native" in table
    private String name;
    private String email;
    private String password;
    private String picture;
}
