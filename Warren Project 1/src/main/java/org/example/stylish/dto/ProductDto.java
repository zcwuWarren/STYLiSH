package org.example.stylish.dto;

public class ProductDto {
    private int id;
    private String category;
    private String title;
    private String description;
    private int price;
    private String texture;
    private String wash;
    private String place;
    private String note;
    private String story;
    private String main_image;

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getTexture() {
        return texture;
    }

    public String getWash() {
        return wash;
    }

    public String getPlace() {
        return place;
    }

    public String getNote() {
        return note;
    }

    public String getStory() {
        return story;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public void setWash(String wash) {
        this.wash = wash;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public ProductDto(int id, String category, String title, String description, int price, String texture, String wash, String place, String note, String story, String main_image) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;
        this.texture = texture;
        this.wash = wash;
        this.place = place;
        this.note = note;
        this.story = story;
        this.main_image = main_image;
    }
}




