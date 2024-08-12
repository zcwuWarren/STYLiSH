package org.example.stylish.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.stylish.dto.VariantForJson;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductSearchResult {
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
    @JsonProperty("main_image")
    private String mainImage;
    private List<String> images;
    private List<VariantForJson> variants;
    private List<Color> colors;
    private List<String> sizes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getWash() {
        return wash;
    }

    public void setWash(String wash) {
        this.wash = wash;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<VariantForJson> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantForJson> variants) {
        this.variants = variants;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public ProductSearchResult() {
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
        this.mainImage = mainImage;
        this.images = images;
        this.variants = variants;
        this.colors = colors;
        this.sizes = sizes;
    }
}
