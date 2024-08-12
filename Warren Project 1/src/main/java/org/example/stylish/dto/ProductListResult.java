package org.example.stylish.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.stylish.model.Product;

import java.util.List;

@Getter
@Setter
public class ProductListResult {
    private List<Product> products;
    private int totalProducts;

    public ProductListResult(List<Product> products, int totalProducts) {
        this.products = products;
        this.totalProducts = totalProducts;
    }
}
