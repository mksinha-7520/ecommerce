package com.ecommerce.service;

import com.ecommerce.entities.Product;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.models.ProductDto;
import com.ecommerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;
    StringBuilder errorMessage=null;

    @Override
    public List<ProductDto> findByProductNameOrProductBrand(String productNameOrBrand) {
        List<ProductDto> retProductDtoList = new ArrayList<>();
        errorMessage= new StringBuilder();
        List<Product> products = this.productRepository.findByProductNameContainingOrProductBrandContaining(productNameOrBrand, productNameOrBrand);
        if(!CollectionUtils.isEmpty(products)) {
            products.stream().forEach(p -> {
                retProductDtoList.add(modelMapper.map(p, ProductDto.class));
            });
        }else{
            errorMessage.append(productNameOrBrand).append(" ").append("Product not Found");
            logger.error(errorMessage.toString());
            throw new ResourceNotFoundException(errorMessage.toString());
        }
        return retProductDtoList;
    }

    @Override
    public ProductDto findByProductId(Integer productId) {
        ProductDto productDto =null;
        errorMessage= new StringBuilder();
        Product product = this.productRepository
                            .findById(productId)
                                .orElseThrow(()-> {
                                            errorMessage.append(productId).append(" ").append("Product not Found");
                                            logger.error(errorMessage.toString());
                                            return  new ResourceNotFoundException(errorMessage.toString());
                                        }
                                );
        productDto = modelMapper.map(product,ProductDto.class);
        return productDto;
    }
}
