package org.example.stylish.rowmapper;

import org.example.stylish.model.Image;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageRowMapper implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Image image = new Image();
        image.setProId(resultSet.getInt("id"));
        image.setImageId(resultSet.getInt("imageId"));
        image.setImageUrl(resultSet.getString("imageUrl"));

        return image;
    }
}
