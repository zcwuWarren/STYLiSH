package org.example.stylish.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.stylish.model.ProductSearchResult;

import java.util.List;

@Getter
@Setter
public class ProductListByKeyword {
    private List<ProductSearchResult> productSearchResults;
    private int totalProductByKeyword;

    public ProductListByKeyword(List<ProductSearchResult> productSearchResults, int totalProductByKeyword) {
        this.productSearchResults = productSearchResults;
        this.totalProductByKeyword = totalProductByKeyword;
    }
}