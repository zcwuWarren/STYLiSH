package org.example.stylish.rowmapper;

import org.example.stylish.model.Size;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SizeRowMapper implements RowMapper<Size> {
    @Override
    public Size mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Size size = new Size();

        size.setId(resultSet.getInt("id"));
        size.setSize(resultSet.getString("size"));

        return size;
    }
}
