package org.example.stylish.service;

import org.example.stylish.model.Image;

public interface ImageService {
    Image createImage(int proId, int imageId, String imageUrl);
}
