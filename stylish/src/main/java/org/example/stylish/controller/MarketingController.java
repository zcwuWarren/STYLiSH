package org.example.stylish.controller;

import org.example.stylish.dto.CampaignDto;
import org.example.stylish.model.CampaignProduct;
import org.example.stylish.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class MarketingController {
    @Autowired
    CampaignService campaignService;

    // query database to list existed product in drop-down list
    @GetMapping("/marketing/products")
    public ResponseEntity<List<CampaignProduct>> getProducts() {
        List<CampaignProduct> campaignProducts = campaignService.getAllProducts();
//        System.out.println("MarketingController: " + campaignProducts);
        return ResponseEntity.ok(campaignProducts);
    }

    // add products of campaign from campaignManagePage
    @PostMapping("/marketing/campaigns")
    public String addCampaign(@RequestParam("productId") int productId, @RequestParam("picture") MultipartFile picture, @RequestParam("story") String story) throws IOException {
        // picture modify Multifile
//        System.out.println("controller.addCampaign: " + productId); //OK
        campaignService.addCampaign(productId, picture, story);
        return "campaignManagePage";
    }

    // query database to get products of campaign
    @GetMapping("api/1.0/marketing/campaigns")
    public ResponseEntity<Map<String, Object>> getCampaign() {
        List<CampaignDto> campaignDto = campaignService.getCampaign();
//        ResponseEntity<?> response = ResponseEntity.ok(campaignDto);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", campaignDto);

        return ResponseEntity.ok(responseBody);
    }
}
