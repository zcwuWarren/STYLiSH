package org.example.stylish.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Size {
    private int sizeId;
    private String sizeName;

    public int getId() {
        return sizeId;
    }

    public void setId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getSize() {
        return sizeName;
    }

    public void setSize(String sizeName) {
        this.sizeName = sizeName;
    }
}
