package org.example.stylish.service.serviceImpl;

import org.example.stylish.dao.SizeDao;
import org.example.stylish.model.Size;
import org.example.stylish.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeDao sizeDao;

    @Override
    public Size createSize(int sizeId, String sizeName) {
        return sizeDao.create(sizeId, sizeName);
    }
}
