package org.example.stylish.rowmapper;

import org.example.stylish.dto.CampaignDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CampaignResponseRowMapper implements RowMapper<CampaignDto> {

    @Override
    public CampaignDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        CampaignDto campaignDto = new CampaignDto();
        campaignDto.setProductId(resultSet.getInt("productId"));
        campaignDto.setPicture(resultSet.getString("picture"));
        campaignDto.setStory(resultSet.getString("story"));

        return campaignDto;
    }
}
