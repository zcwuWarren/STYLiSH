package org.example.stylish.service.serviceImpl;

import org.example.stylish.dao.ColorSetDao;
import org.example.stylish.model.ColorSet;
import org.example.stylish.service.ColorSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorSetServiceImpl implements ColorSetService {

    @Autowired
    private ColorSetDao colorSetDao;

    @Override
    public ColorSet createColorSet(int colorSetId, String colorSetCode) {
        return colorSetDao.create(colorSetId, colorSetCode);
    }
}
