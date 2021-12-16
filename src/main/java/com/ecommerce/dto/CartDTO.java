package com.ecommerce.dto;

import javax.validation.constraints.NotNull;

import com.ecommerce.entity.Cart;

public class CartDTO {

	private Integer id;
    private @NotNull Integer productId;
    private @NotNull Integer quantity;
    private @NotNull Integer userId;
    

    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public CartDTO() {
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ",";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
