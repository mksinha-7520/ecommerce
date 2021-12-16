package com.ecommerce.service;

import com.ecommerce.models.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findByProductNameOrProductBrand(String productNameOrBrand);
    ProductDto findByProductId(Integer productId);
}
