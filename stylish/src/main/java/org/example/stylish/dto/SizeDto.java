package org.example.stylish.dto;

public class SizeDto {
    private int sizeId;
    private String sizeName;

    public int getId() {
        return sizeId;
    }

    public String getSize() {
        return sizeName;
    }

    public void setId(int sizeId) {
        this.sizeId = sizeId;
    }

    public void setSize(String sizeName) {
        this.sizeName = sizeName;
    }

    public SizeDto(int sizeId, String sizeName) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
    }
}
