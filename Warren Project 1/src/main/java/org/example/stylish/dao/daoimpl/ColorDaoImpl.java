package org.example.stylish.dao.daoimpl;

import org.example.stylish.dao.ColorDao;
import org.example.stylish.model.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ColorDaoImpl implements ColorDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Color create(String colorName, String colorCode) {
        // column "name" of color table will field in ":colorName"//
        String sql = "insert into color (name, code) values (:colorName, :colorCode)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("colorName", colorName);
        params.addValue("colorCode", colorCode);

        try {
            namedParameterJdbcTemplate.update(sql, params);
            Color color = new Color();
            color.setColorName(colorName);
            color.setColorCode(colorCode);
            return color;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

