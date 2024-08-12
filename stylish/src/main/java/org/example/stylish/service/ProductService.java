package org.example.stylish.service;

import org.example.stylish.dto.ProductListByKeyword;
import org.example.stylish.dto.ProductListResult;
import org.example.stylish.model.Product;

public interface ProductService {
    Product createProducts(int id, String category, String description, String title, int price, String texture, String wash, String place, String note, String story, String main_image);

    ProductListResult getProductByCategoryWithPaging(String Category, Integer paging) throws Exception;

    Product getProductDetails(long id);

    ProductListByKeyword searchProducts(String keyword, Integer paging) throws Exception;
}

