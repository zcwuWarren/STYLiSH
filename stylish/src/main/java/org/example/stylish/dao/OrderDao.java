package org.example.stylish.dao;

import org.example.stylish.dto.ListDto;
import org.example.stylish.dto.OrderDto;
import org.example.stylish.dto.PaymentResponseDto;
import org.example.stylish.dto.RecipientDto;

public interface OrderDao {
    Integer createOrderDetail(OrderDto orderDto);

    void createRecipient(RecipientDto recipientDto);

    void createListOfOrder(ListDto listDto);

    void checkProductExistence(ListDto listDto);

    void updatePaymentStatus(PaymentResponseDto paymentResponseDto);
}
