package org.example.stylish.rowmapper;

import org.example.stylish.model.CampaignProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CampaignProductRowMapper implements RowMapper<CampaignProduct> {
    @Override
    public CampaignProduct mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        CampaignProduct campaignProduct = new CampaignProduct();
        campaignProduct.setProductId(resultSet.getInt("id"));

        return campaignProduct;
    }

}
