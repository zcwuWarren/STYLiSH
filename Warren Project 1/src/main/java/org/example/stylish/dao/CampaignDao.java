package org.example.stylish.dao;

import org.example.stylish.dto.CampaignDto;
import org.example.stylish.model.Campaign;
import org.example.stylish.model.CampaignProduct;

import java.util.List;

public interface CampaignDao {
    List<CampaignProduct> getAllProducts();

    void addCampaign(Campaign campaign);

    List<CampaignDto> getCampaign();
}
