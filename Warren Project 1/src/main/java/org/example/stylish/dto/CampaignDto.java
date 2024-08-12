package org.example.stylish.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDto {
    @JsonProperty("product_id")
    private int productId;
    private String picture;
    private String story;
}
