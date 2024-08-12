package org.example.stylish.controller;

import org.example.stylish.dto.CheckOutRequestDto;
import org.example.stylish.service.OrderService;
import org.example.stylish.service.serviceImpl.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("api/1.0/order/checkout")
    public ResponseEntity<Map<String, Object>> checkout(@RequestBody CheckOutRequestDto checkOutRequestDto, @RequestHeader("Authorization") String token) {
        // validation for JWT token
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        jwtTokenProvider.validateToken(token);

        // create method and pass checkOutRequestDto to service, then extract specific data into DTOs
        Map<String, Object> response = orderService.createOrder(checkOutRequestDto);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", response);
        return ResponseEntity.ok(responseBody);
    }
}
