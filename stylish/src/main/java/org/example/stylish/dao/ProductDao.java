package org.example.stylish.dao;

import org.example.stylish.dto.ProductListByKeyword;
import org.example.stylish.dto.ProductListResult;
import org.example.stylish.dto.VariantForJson;
import org.example.stylish.model.Color;
import org.example.stylish.model.Product;

import java.util.List;

public interface ProductDao {
    Product create(int id, String category, String description, String title, int price, String texture, String wash, String place, String note, String story, String main_image);

    ProductListResult getProductsByCategory(String category, int offset, int PAGE_SIZE);

    List<Color> getColorsByProductId(long id);

    List<String> getSizeNamesByProductId(long id);

    List<VariantForJson> getVariantsByProductId(long id);

    List<String> getImagesByProductId(long id);

    Product getProductDetailsByProductId(long id);

    // add checkKeywordExist to check exception
    Integer checkKeywordExists(String keyword);

    ProductListByKeyword getProductCountByKeyword(String keyword, int offset, int PAGE_SIZE);

}

