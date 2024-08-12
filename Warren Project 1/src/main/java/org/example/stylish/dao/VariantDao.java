package org.example.stylish.dao;

import org.example.stylish.model.Variant;

public interface VariantDao {
    Variant create(int variantId, String variantColorCode, String variantSize, int stock);
}
