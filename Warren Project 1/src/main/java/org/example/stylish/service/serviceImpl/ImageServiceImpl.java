package org.example.stylish.service.serviceImpl;

import org.example.stylish.dao.ImageDao;
import org.example.stylish.model.Image;
import org.example.stylish.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public Image createImage(int proId, int imageId, String imageUrl) {
        return imageDao.create(proId, imageId, imageUrl);
    }
}
