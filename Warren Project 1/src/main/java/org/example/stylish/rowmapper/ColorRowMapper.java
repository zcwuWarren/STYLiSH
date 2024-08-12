package org.example.stylish.rowmapper;

import org.example.stylish.model.Color;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColorRowMapper implements RowMapper<Color> {


    @Override
    public Color mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Color color = new Color();

        color.setColorName(resultSet.getString("name"));
        color.setColorCode(resultSet.getString("code"));

        return color;
    }
}
