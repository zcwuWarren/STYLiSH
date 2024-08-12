package org.example.stylish.service.serviceImpl;

import org.example.stylish.dao.VariantDao;
import org.example.stylish.model.Variant;
import org.example.stylish.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VariantServiceImpl implements VariantService {
    @Autowired
    private VariantDao variantDao;

    @Override
    public Variant createVariants(int variantId, String variantColorCode, String variantSize, int stock) {
        return variantDao.create(variantId, variantColorCode, variantSize, stock);
    }
}
