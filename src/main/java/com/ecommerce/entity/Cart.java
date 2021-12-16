package com.ecommerce.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "CART")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	@Column(name = "cart_id")
	private Integer cartId;
	
	@NotNull
	@Column(name = "user_id")
	private Integer userId;
	
	@NotNull
	@Column(name = "product_id")
	private Integer productId;
	
	@NotNull
	@Column(name = "quantity")
	private Integer quantity;
	
	@NotNull
	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	@NotNull
	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;

	
	 public Cart(Integer productId, Integer quantity, Integer userId){
	        this.userId = userId;
	        this.productId = productId;
	        this.quantity = quantity;
	        this.cartId = userId;
	        this.creationDate = LocalDateTime.now();
	        this.modifiedDate = LocalDateTime.now();
	    }
	 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
}
