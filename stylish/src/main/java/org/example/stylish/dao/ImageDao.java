package org.example.stylish.dao;

import org.example.stylish.model.Image;

public interface ImageDao {
    Image create(int id, int imageId, String imageUrl);

}
