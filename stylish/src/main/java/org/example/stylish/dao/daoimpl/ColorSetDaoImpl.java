package org.example.stylish.dao.daoimpl;

import org.example.stylish.dao.ColorSetDao;
import org.example.stylish.model.ColorSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ColorSetDaoImpl implements ColorSetDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public ColorSet create(int colorSetId, String colorSetCode) {
        String sql = "insert into color_set (id, code) values (:colorSetId, :colorSetCode)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("colorSetId", colorSetId);
        params.addValue("colorSetCode", colorSetCode);


        try {
            namedParameterJdbcTemplate.update(sql, params);
            ColorSet colorSet = new ColorSet();
            colorSet.setId(colorSetId);
            colorSet.setColorCode(colorSetCode);
            return colorSet;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
