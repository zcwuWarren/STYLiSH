package org.example.stylish.dao;

import org.example.stylish.model.Color;

public interface ColorDao {
    Color create(String colorName, String colorCode);
}
