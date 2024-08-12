package org.example.stylish.dao.daoimpl;

import org.example.stylish.dao.ImageDao;
import org.example.stylish.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ImageDaoImpl implements ImageDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Image create(int proId, int imageId, String imageUrl) {

        String sql = "insert into image (id, imageId, imageUrl) values (:proId, :imageId, :imageUrl)";

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("proId", proId);
        params.addValue("imageId", imageId);
        params.addValue("imageUrl", imageUrl);

        try {
            namedParameterJdbcTemplate.update(sql, params);
            Image image = new Image();
            image.setProId(proId);
            image.setImageId(imageId);
            image.setImageUrl(imageUrl);
            return image;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
