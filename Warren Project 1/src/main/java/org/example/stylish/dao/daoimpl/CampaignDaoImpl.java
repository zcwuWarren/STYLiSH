package org.example.stylish.dao.daoimpl;

import org.example.stylish.dao.CampaignDao;
import org.example.stylish.dto.CampaignDto;
import org.example.stylish.model.Campaign;
import org.example.stylish.model.CampaignProduct;
import org.example.stylish.rowmapper.CampaignProductRowMapper;
import org.example.stylish.rowmapper.CampaignResponseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CampaignDaoImpl implements CampaignDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<CampaignProduct> getAllProducts() {
        String sql = "SELECT id FROM product";
        MapSqlParameterSource params = new MapSqlParameterSource();
        List<CampaignProduct> campaignProducts = namedParameterJdbcTemplate.query(sql, params, new CampaignProductRowMapper());
//        System.out.println("CampaignDaoImpl: " + campaignProducts);
        return campaignProducts;
    }

    @Override
    public void addCampaign(Campaign campaign) {

        String sql = "INSERT INTO campaign (productId, picture, story) VALUES (:productId, :picture, :story)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("productId", campaign.getProductId())
                .addValue("picture", campaign.getPicture())
                .addValue("story", campaign.getStory());
        namedParameterJdbcTemplate.update(sql, params);
//        System.out.println("CampaignDaoImpl: " + campaign.getPicture());
    }

    @Override
    public List<CampaignDto> getCampaign() {
        String sql = "SELECT * FROM campaign";
//        System.out.println(sql);
        MapSqlParameterSource params = new MapSqlParameterSource();
        // use .query to return multi result in List<>
        List<CampaignDto> campaignDto = namedParameterJdbcTemplate.query(sql, params, new CampaignResponseRowMapper());
//        System.out.println("CampaingDaoImpl.getCampaign: " + campaignDto.get(0).getPicture());
        return campaignDto;
    }
}
