package com.ecommerce.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Product;
import com.ecommerce.exceptionhandling.ResourceNotFoundException;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;

@Service
@Transactional
public class EcommerceServiceImpl{

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(EcommerceServiceImpl.class);
	
	public String addToCart(CartDTO cartdto) throws ResourceNotFoundException {
		
		logger.info("-----EcommerceServiceImpl.addToCart() called---------");
		
		checkAvailableQuantity(cartdto.getQuantity(),cartdto.getProductId());
		Cart cart = new Cart(cartdto.getProductId(), cartdto.getQuantity(), cartdto.getUserId());
		cartRepository.save(cart);
		logger.info("-----EcommerceServiceImpl.addToCart() Product added in cart Successfully---------");
		return "Product added in cart Successfully";
	}

	private void checkAvailableQuantity(Integer quantity, Integer product_id) throws ResourceNotFoundException{
		
			Product product = productRepository.findById(product_id).orElseThrow(() -> new ResourceNotFoundException("Invalid Product Id :: " + product_id)); 
			
			if(product.getProductQty() < quantity) {
				logger.error("Please reduce the quantity of Product!");
				throw new ResourceNotFoundException("Please reduce the quantity of Product!");
			}else {
				product.setProductQty(product.getProductQty()-quantity);
				productRepository.save(product);
			}
	}
	

    
}
