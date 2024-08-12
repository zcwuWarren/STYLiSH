package org.example.stylish.controller;

import org.example.stylish.dto.*;
import org.example.stylish.model.ColorSet;
import org.example.stylish.service.ColorSetService;
import org.example.stylish.service.VariantService;
import org.example.stylish.service.serviceImpl.ColorServiceImpl;
import org.example.stylish.service.serviceImpl.ImageServiceImpl;
import org.example.stylish.service.serviceImpl.ProductServiceImpl;
import org.example.stylish.service.serviceImpl.SizeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@Controller
public class AdminController {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private ColorServiceImpl colorService;
    @Autowired
    private SizeServiceImpl sizeService;
    @Autowired
    private ImageServiceImpl imageService;
    @Autowired
    private ColorSetService colorSetService;
    @Autowired
    private VariantService variantService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/admin/product")
    public String showProductForm() {
        return "productManagePage";
    }

    @GetMapping("/admin/campaign")
    public String showCampaignForm() {
        return "campaignManagePage";
    }

    @GetMapping("/admin/checkout.html")
    public String showCheckoutPage() {
        return "checkout";
    }

    @PostMapping("/productManaging")
    public String productManage(ProductDto productDto, ColorDto colorDto, SizeDto sizeDto, ImageDto imageDto, ColorSetDto colorSetDto, VariantDto variantDto, @RequestParam("file") MultipartFile file, @RequestParam("mainFile") MultipartFile mainFile) throws IOException {
        checkProduct(productDto, mainFile);
        checkColor(colorDto);
        checkSize(sizeDto);
        checkImage(imageDto, file);
        checkColorSet(colorSetDto);
        checkVariant(variantDto);
        return "productManagePage";
    }

    public void checkProduct(ProductDto productDto, MultipartFile mainFile) throws IOException { /*return to same admin page to continue add product*/
        int id = productDto.getId();
        String category = productDto.getCategory();
        String description = productDto.getDescription();
        String title = productDto.getTitle();
        int price = productDto.getPrice();
        String texture = productDto.getTexture();
        String wash = productDto.getWash();
        String place = productDto.getPlace();
        String note = productDto.getNote();
        String story = productDto.getStory();
        String mainFileName = mainFile.getOriginalFilename();

        Path filePath = Paths.get(uploadDir + File.separator + mainFileName);
        Files.write(filePath, mainFile.getBytes());
        // for EC2
        String fileLink = "http://54.168.221.156/images/" + mainFileName;
        // for local test
//        String fileLink = uploadDir + mainFileName;
        productDto.setMain_image(fileLink);
        productService.createProducts(id, category, description, title, price, texture, wash, place, note, story, fileLink); // colorName is variable name
    }

    public void checkColor(ColorDto colorDto) {
        String colorName = colorDto.getColorName();
        String colorCode = colorDto.getColorCode();
        colorService.creatColor(colorName, colorCode); // colorName is variable name
    }

    public void checkSize(SizeDto sizeDto) {
        int sizeId = sizeDto.getId();
        String sizeName = sizeDto.getSize();
        sizeService.createSize(sizeId, sizeName);
    }

    public void checkImage(ImageDto imageDto, MultipartFile file) throws IOException {
        int proId = imageDto.getId();
        int imageId = imageDto.getImageId();
        String fileName = file.getOriginalFilename();


        Path filePath = Paths.get(uploadDir + File.separator + fileName);
        Files.write(filePath, file.getBytes());
        // for EC2
        String fileLink = "http://54.168.221.156/images/" + fileName;
        // for local test
//        String fileLink = uploadDir + fileName;

        imageDto.setImageUrl(fileLink);
        imageService.createImage(proId, imageId, fileLink);
    }

    public void checkColorSet(ColorSetDto colorSetDto) {
        int colorSetId = colorSetDto.getId();
        String colorSetCode = colorSetDto.getColorCode();
        ColorSet colorSet = colorSetService.createColorSet(colorSetId, colorSetCode);

    }

    public void checkVariant(VariantDto variantDto) {
        int variantId = variantDto.getId();
        String variantColorCode = variantDto.getColorCode();
        String variantSize = variantDto.getSize();
        int stock = variantDto.getStock();
        variantService.createVariants(variantId, variantColorCode, variantSize, stock);
    }
}
