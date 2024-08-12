package org.example.stylish.rowmapper;

import org.example.stylish.model.ColorSet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColorSetRowMapper implements RowMapper<ColorSet> {

    @Override
    public ColorSet mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ColorSet colorSet = new ColorSet();

        colorSet.setId(resultSet.getInt("id"));
        colorSet.setColorCode(resultSet.getString("code"));

        return colorSet;
    }
}
