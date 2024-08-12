package org.example.stylish.dao.daoimpl;

import org.example.stylish.dao.SizeDao;
import org.example.stylish.model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SizeDaoImpl implements SizeDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    // notice the String variable name "sizeName", can't be duplicated with Size model name "size"
    public Size create(int sizeId, String sizeName) {
        String sql = "insert into size (id, size) values (:sizeId, :sizeName)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("sizeId", sizeId);
        params.addValue("sizeName", sizeName);

        try {
            namedParameterJdbcTemplate.update(sql, params);
            Size size = new Size();
            size.setId(sizeId);
            size.setSize(sizeName);
            return size;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

