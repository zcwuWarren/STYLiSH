package org.example.stylish.service.serviceImpl;

import com.zaxxer.hikari.HikariDataSource;
import org.example.stylish.dao.ProductDao;
import org.example.stylish.dto.ProductListByKeyword;
import org.example.stylish.dto.ProductListResult;
import org.example.stylish.exception.ProductApiException;
import org.example.stylish.model.Product;
import org.example.stylish.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    // set PAGE_SIZE to specif the count of listed products in single page
    private final int PAGE_SIZE = 6;
    private List<String> categoryType = List.of("all", "men", "women", "accessories");

    @Autowired
    private ProductDao productDao;
    @Autowired
    private HikariDataSource dataSource;

    @Override
    public Product createProducts(int id, String category, String description, String title, int price, String texture, String wash, String place, String note, String story, String main_image) {
        return productDao.create(id, category, description, title, price, texture, wash, place, note, story, main_image);
    }

    @Override
    public ProductListResult getProductByCategoryWithPaging(String category, Integer paging) {
        // set offset depend on current page
        int offset = (paging != null) ? paging * PAGE_SIZE : 0;
        if (categoryType.contains(category)) {
            ProductListResult productListResult = productDao.getProductsByCategory(category, offset, PAGE_SIZE);
            return productListResult;
        } else {
            throw new ProductApiException("Invalid category: " + category);
        }
    }

    @Override
    public Product getProductDetails(long id) {
        Product productDetails = productDao.getProductDetailsByProductId(id);
        return productDetails;
    }

    @Override
    public ProductListByKeyword searchProducts(String keyword, Integer paging) {
        int offset = (paging != null) ? paging * PAGE_SIZE : 0;

        try {
            Integer keywordCount = productDao.checkKeywordExists(keyword);
            if (keywordCount <= 0) {
                throw new ProductApiException("No related keyword products found: " + keyword);
            }
            ProductListByKeyword productListByKeyword = productDao.getProductCountByKeyword(keyword, offset, PAGE_SIZE);
            return productListByKeyword;
        } catch (ProductApiException e) {
            throw e;
        }
    }


}
