package org.example.stylish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String shipping;
    private String payment;
    private String subtotal;
    private String freight;
    private String total;
    private RecipientDto recipient;
    private List<ListDto> list;
}
