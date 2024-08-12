package org.example.stylish.dto;

public class ColorSetDto {
    private int colorSetId;
    private String colorSetCode;

    public ColorSetDto(int colorSetId, String colorSetCode) {
        this.colorSetId = colorSetId;
        this.colorSetCode = colorSetCode;
    }
    

    public int getId() {
        return colorSetId;
    }

    public String getColorCode() {
        return colorSetCode;
    }

    public void setId(int colorSetId) {
        this.colorSetId = colorSetId;
    }

    public void setColorCode(String colorSetCode) {
        this.colorSetCode = colorSetCode;
    }
}
