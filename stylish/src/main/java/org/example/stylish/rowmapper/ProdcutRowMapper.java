package org.example.stylish.rowmapper;

import org.example.stylish.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdcutRowMapper implements org.springframework.jdbc.core.RowMapper {

    @Override
    public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Product product = new Product();

        product.setId(resultSet.getInt("id"));
        product.setCategory(resultSet.getString("category"));
        product.setDescription(resultSet.getString("description"));
        product.setTitle(resultSet.getString("title"));
        product.setPrice(resultSet.getInt("price"));
        product.setTexture(resultSet.getString("texture"));
        product.setWash(resultSet.getString("wash"));
        product.setPlace(resultSet.getString("place"));
        product.setNote(resultSet.getString("note"));
        product.setStory(resultSet.getString("story"));
        product.setMainImage(resultSet.getString("main_image"));

        return product;
    }
}
