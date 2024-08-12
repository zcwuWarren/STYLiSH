package org.example.stylish.rowmapper;

import org.example.stylish.dto.VariantForJson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

// create a new RowMapper class for map certain field in variant table
public class VariantForJsonRowMapper implements RowMapper<VariantForJson> {

    @Override
    public VariantForJson mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        VariantForJson variantForJson = new VariantForJson();
        variantForJson.setColorCode(resultSet.getString("code"));
        variantForJson.setSize(resultSet.getString("size"));
        variantForJson.setStock(resultSet.getInt("stock"));

        return variantForJson;
    }
}
