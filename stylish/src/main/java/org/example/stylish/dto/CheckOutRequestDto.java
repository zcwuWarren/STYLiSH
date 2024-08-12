package org.example.stylish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutRequestDto {
    private String prime;
    private OrderDto order;
}
