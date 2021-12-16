package com.ecommerce.controller;

import com.ecommerce.models.ProductDto;
import com.ecommerce.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("api/products")
@Validated
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity findByProductNameOrProductBrand(@RequestParam(required = true)
                                                               @Valid @NotBlank(message = "ProductNameOrBrand must not be empty" ) String productNameOrBrand) {
        List<ProductDto> retProductDtoList = productService.findByProductNameOrProductBrand(productNameOrBrand);
        return new ResponseEntity<>(retProductDtoList, HttpStatus.OK);

    }

    @GetMapping("/{productId}")
    public ResponseEntity findByProductId(@PathVariable Integer productId) {
        ProductDto productDto = productService.findByProductId(productId);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }


}

