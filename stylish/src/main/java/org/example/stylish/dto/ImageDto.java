package org.example.stylish.dto;

public class ImageDto {
    private int proId;
    private int imageId;
    private String imageUrl;

    public int getId() {
        return proId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(int proId) {
        this.proId = proId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ImageDto(int proId, int imageId, String imageUrl) {
        this.proId = proId;
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }
}
