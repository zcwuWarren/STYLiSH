package org.example.stylish.rowmapper;

import org.example.stylish.model.ProductSearchResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductSearchRowMapper implements RowMapper<ProductSearchResult> {
    @Override
    public ProductSearchResult mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ProductSearchResult productSearchResult = new ProductSearchResult();
        productSearchResult.setId(resultSet.getInt("id"));
        productSearchResult.setCategory(resultSet.getString("category"));
        productSearchResult.setTitle(resultSet.getString("title"));
        productSearchResult.setDescription(resultSet.getString("description"));
        productSearchResult.setPrice(resultSet.getInt("price"));
        productSearchResult.setTexture(resultSet.getString("texture"));
        productSearchResult.setWash(resultSet.getString("wash"));
        productSearchResult.setPlace(resultSet.getString("place"));
        productSearchResult.setNote(resultSet.getString("note"));
        productSearchResult.setStory(resultSet.getString("story"));
        productSearchResult.setMainImage(resultSet.getString("main_image"));

        return productSearchResult;
    }
}
