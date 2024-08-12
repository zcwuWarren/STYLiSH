package org.example.stylish.service.serviceImpl;

import org.example.stylish.dao.OrderDao;
import org.example.stylish.dto.*;
import org.example.stylish.model.Color;
import org.example.stylish.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Map<String, Object> createOrder(CheckOutRequestDto checkOutRequestDto) {
        Color color = new Color();
        color.setColorCode(checkOutRequestDto.getOrder().getList().get(0).getColor().getColorCode());
        color.setColorName(checkOutRequestDto.getOrder().getList().get(0).getColor().getColorName());

        ListDto listDto = new ListDto();
        listDto.setId(checkOutRequestDto.getOrder().getList().get(0).getId());
        listDto.setName(checkOutRequestDto.getOrder().getList().get(0).getName());
        listDto.setPrice(checkOutRequestDto.getOrder().getList().get(0).getPrice());
        listDto.setColor(color);
        listDto.setSize(checkOutRequestDto.getOrder().getList().get(0).getSize());
        listDto.setQty(checkOutRequestDto.getOrder().getList().get(0).getQty());
        // check product id existence
        orderDao.checkProductExistence(listDto);

        //pass listDto to Dao
        orderDao.createListOfOrder(listDto);


        RecipientDto recipientDto = new RecipientDto();
        recipientDto.setName(checkOutRequestDto.getOrder().getRecipient().getName());
        recipientDto.setPhone(checkOutRequestDto.getOrder().getRecipient().getPhone());
        recipientDto.setEmail(checkOutRequestDto.getOrder().getRecipient().getEmail());
        recipientDto.setAddress(checkOutRequestDto.getOrder().getRecipient().getAddress());
        recipientDto.setTime(checkOutRequestDto.getOrder().getRecipient().getTime());
        //  pass recipientDto to Dao
        orderDao.createRecipient(recipientDto); //OK
        OrderDto orderDto = new OrderDto();
        orderDto.setShipping(checkOutRequestDto.getOrder().getShipping());
        orderDto.setPayment(checkOutRequestDto.getOrder().getPayment());
        orderDto.setSubtotal(checkOutRequestDto.getOrder().getSubtotal());
        orderDto.setFreight(checkOutRequestDto.getOrder().getFreight());
        orderDto.setTotal(checkOutRequestDto.getOrder().getTotal());
        // pass order to Dao
        // check whether product existed
        Integer orderId = orderDao.createOrderDetail(orderDto); //OK
        // get the orderId
        Map<String, Object> result = new HashMap<>();
        result.put("number", orderId);

        // call TapPay payment API
        PaymentResponseDto paymentResponseDto = processPayment(checkOutRequestDto);
        // check payment status
        if (paymentResponseDto.getStatus() != 0) {
            throw new RuntimeException("Payment failed");
        }
        // update payment status in database
        orderDao.updatePaymentStatus(paymentResponseDto);

        return result;
    }

    @Override
    public PaymentResponseDto processPayment(CheckOutRequestDto checkOutRequestDto) {
        RestTemplate restTemplate = new RestTemplate();

        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setPrime(checkOutRequestDto.getPrime());
        paymentRequestDto.setPartnerKey("partner_PHgswvYEk4QY6oy3n8X3CwiQCVQmv91ZcFoD5VrkGFXo8N7BFiLUxzeG");
        paymentRequestDto.setMerchantId("AppWorksSchool_CTBC");
        paymentRequestDto.setDetails("stylish");
        paymentRequestDto.setAmount(Integer.parseInt(checkOutRequestDto.getOrder().getTotal()));

        PaymentRequestDto.Cardholder cardholder = new PaymentRequestDto.Cardholder();
        cardholder.setPhoneNumber(checkOutRequestDto.getOrder().getRecipient().getPhone());
        cardholder.setName(checkOutRequestDto.getOrder().getRecipient().getName());
        cardholder.setEmail(checkOutRequestDto.getOrder().getRecipient().getEmail());
        paymentRequestDto.setCardholder(cardholder);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "partner_PHgswvYEk4QY6oy3n8X3CwiQCVQmv91ZcFoD5VrkGFXo8N7BFiLUxzeG");

        HttpEntity<PaymentRequestDto> entity = new HttpEntity<>(paymentRequestDto, headers);

        URI url = UriComponentsBuilder.fromHttpUrl("https://sandbox.tappaysdk.com/tpc/payment/pay-by-prime")
                .build()
                .toUri();

        ResponseEntity<PaymentResponseDto> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, PaymentResponseDto.class);
        PaymentResponseDto response = responseEntity.getBody();

        return response;
    }
}
