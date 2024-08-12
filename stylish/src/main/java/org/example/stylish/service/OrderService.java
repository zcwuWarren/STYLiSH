package org.example.stylish.service;

import org.example.stylish.dto.CheckOutRequestDto;
import org.example.stylish.dto.PaymentResponseDto;

import java.util.Map;

public interface OrderService {
    Map<String, Object> createOrder(CheckOutRequestDto checkOutRequestDto);

    PaymentResponseDto processPayment(CheckOutRequestDto checkOutRequestDto);
}
