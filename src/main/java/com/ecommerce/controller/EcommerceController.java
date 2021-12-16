package com.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.exceptionhandling.ResourceNotFoundException;
import com.ecommerce.service.EcommerceServiceImpl;


@RestController
@RequestMapping(path = "/api")
public class EcommerceController {

	@Autowired
	EcommerceServiceImpl ecommerceService;
	
	private static final Logger logger = LoggerFactory.getLogger(EcommerceController.class);
	
	@PostMapping("/users/carts")
	public String addToCart(@RequestBody CartDTO cartdto) throws ResourceNotFoundException {
		logger.info("-----EcommerceController.addToCart() method called-----");
		String message = ecommerceService.addToCart(cartdto);

		return message;
	}
}
