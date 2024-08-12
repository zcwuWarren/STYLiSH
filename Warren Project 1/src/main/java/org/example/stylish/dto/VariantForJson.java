package org.example.stylish.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
// // create a new  class for map certain field in variant table
public class VariantForJson {
    @JsonProperty("color_code")
    private String colorCode;
    @JsonProperty("size")
    private String size;
    @JsonProperty("stock")
    private int stock;

    public VariantForJson(String colorCode, String size, int stock) {
        this.colorCode = colorCode;
        this.size = size;
        this.stock = stock;
    }

    public VariantForJson() {
    }
}
