package org.example.stylish.dto;

public class VariantDto {
    private int variantId;
    private String variantColorCode;
    private String variantSize;
    private int stock;

    public int getId() {
        return variantId;
    }

    public String getColorCode() {
        return variantColorCode;
    }

    public String getSize() {
        return variantSize;
    }

    public int getStock() {
        return stock;
    }

    public void setId(int variantId) {
        this.variantId = variantId;
    }

    public void setColorCode(String variantColorCode) {
        this.variantColorCode = variantColorCode;
    }

    public void setSize(String variantSize) {
        this.variantSize = variantSize;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public VariantDto(int variantId, String variantColorCode, String variantSize, int stock) {
        this.variantId = variantId;
        this.variantColorCode = variantColorCode;
        this.variantSize = variantSize;
        this.stock = stock;
    }
}


