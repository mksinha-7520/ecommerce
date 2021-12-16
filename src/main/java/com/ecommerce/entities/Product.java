package com.ecommerce.entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "product")
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @Column(name = "product_name", nullable = false, length = 45)
    private String productName;

    @Column(name = "product_brand", nullable = false, length = 45)
    private String productBrand;

    @Column(name = "product_batch_id", nullable = false, length = 45)
    private String productBatchId;

    @Column(name = "product_manf_date", nullable = false, length = 45)
    private LocalDate productManfDate;

    @Column(name = "product_qty", nullable = false)
    private Integer productQty;

    @Column(name = "product_price", nullable = false)
    private Double productPrice;

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }

    public LocalDate getProductManfDate() {
        return productManfDate;
    }

    public void setProductManfDate(LocalDate productManfDate) {
        this.productManfDate = productManfDate;
    }

    public String getProductBatchId() {
        return productBatchId;
    }

    public void setProductBatchId(String productBatchId) {
        this.productBatchId = productBatchId;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}