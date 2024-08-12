package org.example.stylish.rowmapper;


import org.example.stylish.model.Variant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VariantRowMapper implements RowMapper<Variant> {

    @Override
    public Variant mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Variant variant = new Variant();
        variant.setId(resultSet.getInt("id"));
        variant.setColorCode(resultSet.getString("code"));
        variant.setSize(resultSet.getString("size"));
        variant.setStock(resultSet.getInt("stock"));

        return variant;
    }
}
