package com.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto implements Serializable {
    private  Integer id;
    private  String productName;
    private  String productBrand;
    private LocalDate productManfDate;
    private  Integer productQty;
    private  Double productPrice;
}
