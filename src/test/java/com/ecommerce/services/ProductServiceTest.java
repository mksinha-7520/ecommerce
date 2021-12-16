package com.ecommerce.services;

import com.ecommerce.entities.Product;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.models.ProductDto;
import com.ecommerce.repositories.ProductRepository;
import com.ecommerce.service.ProductServiceImpl;
import com.prj.ecomm.products.exceptions.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;
    @Mock
    ModelMapper modelMapper;

    private Product product1,product2;
    private ProductDto productDto1,productDto2;

    List<Product> productList ;
    List<ProductDto> productDtoList ;

    @BeforeEach
    public void setUp() {
        productList = new ArrayList<>();
        product1 = new Product();
        product1.setId(1);
        productDto1 = new ProductDto();
        productDto1.setId(1);

        product2 = new Product();
        product2.setId(2);
        productDto2 = new ProductDto();
        productDto2.setId(1);

        productList.add(product1);
        productList.add(product2);
    }

    @AfterEach
    public void tearDown() {
        product1 =  null;
        product2 =  null;
        productDto1 =  null;
        productDto2 =  null;
        productList = null;
    }

    @Test
    public void findByProductIdTest(){
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.ofNullable(product1));
        Mockito.when(modelMapper.map(product1, ProductDto.class)).thenReturn(productDto1);
        ProductDto retProductDto = productService.findByProductId(1);
        Assertions.assertThat(retProductDto.getId()).isEqualTo(productDto1.getId());
    }

    @Test
    public void findByProductIdExceptionTest(){
       Mockito.when(productRepository.findById(1)).thenReturn(Optional.empty());
       Throwable throwable =  assertThrows(ResourceNotFoundException.class, () ->productService.findByProductId(1) );
       assert(throwable.getMessage().contains("Product not Found"));
    }

    @Test
    public void findByProductNameOrProductBrandTest(){
        Mockito.when(this.productRepository
                .findByProductNameContainingOrProductBrandContaining("brand","brand"))
                .thenReturn(productList);
        Mockito.when(modelMapper.map(any(Product.class), any())).thenReturn(productDto1).thenReturn(productDto2);
        List<ProductDto> retProductDtoList = this.productService.findByProductNameOrProductBrand("brand");
        assertEquals(retProductDtoList.size(),2);

    }
    @Test
    public void findByProductNameOrProductBrandExceptionTest(){
        Mockito.when(this.productRepository
                        .findByProductNameContainingOrProductBrandContaining("unknown brand","unknown brand"))
                .thenReturn(null);
        Throwable throwable = assertThrows(ResourceNotFoundException.class, ()->this.productService.findByProductNameOrProductBrand("unknown brand"));
        assert(throwable.getMessage().contains("Product not Found")) ;
    }

}