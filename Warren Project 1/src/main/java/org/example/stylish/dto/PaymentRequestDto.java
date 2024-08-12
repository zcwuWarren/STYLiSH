package org.example.stylish.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {
    private String prime;
    @JsonProperty("partner_key")
    private String partnerKey;
    @JsonProperty("merchant_id")
    private String merchantId;
    private String details;
    private int amount;
    private Cardholder cardholder;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Cardholder {
        @JsonProperty("phone_number")
        private String phoneNumber;
        private String name;
        private String email;
    }
}
