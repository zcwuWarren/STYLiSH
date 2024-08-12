package org.example.stylish.controller;


import org.example.stylish.dto.ProductListByKeyword;
import org.example.stylish.dto.ProductListResult;
import org.example.stylish.model.Product;
import org.example.stylish.model.ProductSearchResult;
import org.example.stylish.service.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;
    private final int PAGE_SIZE = 6;

    @GetMapping("api/1.0/products/{category}")
    public ResponseEntity<Map<String, Object>> getProductsByCategory(@PathVariable("category") String category, @RequestParam(value = "paging", required = false) Integer paging) {
        // if there is no given paging, set default paging = 0
        if (paging == null) {
            paging = 0;
        }
        ProductListResult productListResult = productService.getProductByCategoryWithPaging(category, paging);
        List<Product> products = productListResult.getProducts();

        int totalProductsByCategory = productListResult.getTotalProducts();
        /* nextPage condition: if there is given paging, check the next page which will fulfill all product in specified category
        if true, nextPage = paging + 1; if false, nextPage = null
        * */
        Integer nextPage = (paging != null && (paging + 1) * PAGE_SIZE < totalProductsByCategory) ? paging + 1 : null;


        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", products);
        if (nextPage != null) {
            responseBody.put("next_paging", nextPage);
        }
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("api/1.0/products/details")
    public ResponseEntity<Map<String, Object>> getProductDetailsByCategory(@RequestParam("id") Integer id) {
        Product productDetails = productService.getProductDetails(id);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", productDetails);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/api/1.0/products/search")
    public ResponseEntity<Map<String, Object>> getProductsBySearch(@RequestParam(value = "keyword") String keyword,
                                                                   @RequestParam(value = "paging", required = false) Integer paging) throws Exception {
        if (paging == null) {
            paging = 0;
        }

        ProductListByKeyword productListByKeyword = productService.searchProducts(keyword, paging);
        List<ProductSearchResult> productSearchResults = productListByKeyword.getProductSearchResults(); // sth wrong

        int totalProductsByKeyword = productListByKeyword.getTotalProductByKeyword();
        Integer nextPage = (paging != null && (paging + 1) * PAGE_SIZE < totalProductsByKeyword) ? paging + 1 : null;

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", productSearchResults);

        if (nextPage != null) {
            responseBody.put("next_paging", nextPage);
        }
        return ResponseEntity.ok(responseBody);
    }
}

