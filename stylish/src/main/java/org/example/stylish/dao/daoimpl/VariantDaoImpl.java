package org.example.stylish.dao.daoimpl;

import org.example.stylish.dao.VariantDao;
import org.example.stylish.model.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class VariantDaoImpl implements VariantDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Variant create(int variantId, String variantColorCode, String variantSize, int stock) {

        String sql = "insert into variant (id, code, size, stock) values (:variantId, :variantColorCode, :variantSize, :stock)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("variantId", variantId);
        params.addValue("variantColorCode", variantColorCode);
        params.addValue("variantSize", variantSize);
        params.addValue("stock", stock);

        try {
            namedParameterJdbcTemplate.update(sql, params);
            Variant variant = new Variant();
            variant.setColorCode(variantColorCode);
            variant.setSize(variantSize);
            variant.setStock(stock);

            return variant;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
