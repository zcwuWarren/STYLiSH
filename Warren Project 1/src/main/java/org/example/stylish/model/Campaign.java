package org.example.stylish.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campaign {
    private int productId;
    private String picture;
    private String story;
}
