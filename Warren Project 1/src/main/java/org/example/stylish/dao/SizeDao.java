package org.example.stylish.dao;

import org.example.stylish.model.Size;

public interface SizeDao {
    Size create(int sizeId, String sizeName);
}
