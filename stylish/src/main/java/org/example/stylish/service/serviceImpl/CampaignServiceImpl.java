package org.example.stylish.service.serviceImpl;

import org.example.stylish.dao.CampaignDao;
import org.example.stylish.dto.CampaignDto;
import org.example.stylish.model.Campaign;
import org.example.stylish.model.CampaignProduct;
import org.example.stylish.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignDao campaignDao;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addCampaign(int productId, MultipartFile picture, String story) throws IOException {
        Campaign campaign = new Campaign();
        campaign.setProductId(productId);
        String fileName = picture.getOriginalFilename();
        campaign.setStory(story);

        Path filePath = Paths.get(uploadDir + File.separator + fileName);
        Files.write(filePath, picture.getBytes());
        // for EC2
        String fileLink = "http://54.168.221.156/images/" + fileName;
        // for local test
//        String fileLink = uploadDir + fileName;
        campaign.setPicture(fileLink);
//        System.out.println("CampaignServiceImpl: " + campaign.getPicture()); // OK

        campaignDao.addCampaign(campaign);
    }

    @Override
    public List<CampaignProduct> getAllProducts() {
        return campaignDao.getAllProducts();
    }

    @Override
    public List<CampaignDto> getCampaign() {
        List<CampaignDto> campaignDto = campaignDao.getCampaign();
        return campaignDto;
    }
}
