package com.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.exceptionhandling.ResourceNotFoundException;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.EcommerceServiceImpl;

@SpringBootTest
class EcommerceApplicationTests {
	
	@InjectMocks
	EcommerceServiceImpl ecommerceServiceImpl;

	@Mock
	CartRepository cartRepository;
	
	@Mock
	ProductRepository productRepository;
	
	
	@Test
	public void checkInvalidProduct() {
		CartDTO cartDTO = getCartDTO(109, 1, 102);
		try {
			ecommerceServiceImpl.addToCart(cartDTO);
		} catch (ResourceNotFoundException e) {
			assertEquals(e.getMessage(), "Invalid Product Id :: 109");
		}
	}
	
	@Test
	public void checkQuantityOfProduct()  {
		CartDTO cartDTO = getCartDTO(102,10,102);
		try {
			 ecommerceServiceImpl.addToCart(cartDTO);
		} catch (ResourceNotFoundException exp) {
			assertEquals(exp.getMessage(),"Please reduce the quantity of Product!");
		}
	}
	
	@Test
	public void addValidProduct() throws ResourceNotFoundException  {
		CartDTO cartDTO = getCartDTO(102,1,102);
		String message = ecommerceServiceImpl.addToCart(cartDTO);
		assertEquals(message,"Product added in cart Successfully");
	}


	private CartDTO getCartDTO(int productid, int quantity, int userid) {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setProductId(productid);
		cartDTO.setQuantity(quantity);
		cartDTO.setUserId(userid);
		return cartDTO;
	}

}
