package org.example.stylish.service;

import org.example.stylish.model.Variant;

public interface VariantService {
    Variant createVariants(int variantId, String variantColorCode, String variantSize, int stock);
}
