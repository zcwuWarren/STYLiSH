package org.example.stylish.service;

import org.example.stylish.dto.CampaignDto;
import org.example.stylish.model.CampaignProduct;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CampaignService {
    void addCampaign(int productId, MultipartFile picture, String story) throws IOException;

    List<CampaignProduct> getAllProducts();

    List<CampaignDto> getCampaign();
}
