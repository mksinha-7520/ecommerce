package com.ecommerce.service;


import com.ecommerce.dto.CartDTO;
import com.ecommerce.exceptionhandling.ResourceNotFoundException;


public interface EcommerceService {

	public String addToCart(CartDTO cartdto) throws ResourceNotFoundException;
}
