package org.example.stylish.service.serviceImpl;

import org.example.stylish.dao.ColorDao;
import org.example.stylish.model.Color;
import org.example.stylish.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorDao colorDao;

    @Override
    public Color creatColor(String colorName, String colorCode) {
        return colorDao.create(colorName, colorCode);
    }
}
