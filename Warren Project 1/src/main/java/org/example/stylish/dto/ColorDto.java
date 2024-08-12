package org.example.stylish.dto;

public class ColorDto {
    private String colorName;
    private String colorCode;

    public String getColorName() {
        return colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public ColorDto(String colorName, String colorCode) {
        this.colorName = colorName;
        this.colorCode = colorCode;
    }
}




