package org.example.stylish.dao;

import org.example.stylish.model.ColorSet;

public interface ColorSetDao {
    ColorSet create(int colorSetId, String colorSetCode);
}
