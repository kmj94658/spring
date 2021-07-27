package com.kh.productapp.product.dao;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.kh.productapp.product.dto.ProductDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional //테스트하고 다시 롤백시킨다
public class ProductDAOImplTest {
	
	@Autowired
	private JdbcTemplate jt;
	private ProductDAOImpl productDAOImpl;
	
	@Test
	@DisplayName("상품등록")
	@Disabled
	void addProduct() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId("4");
		productDTO.setName("블라우스");
		productDTO.setStock(2);
		productDTO.setPrice(30000);
		
		//ProductDTO storedProductDTO = productDAOImpl.addProduct(productDTO);
		//Assertions.assertThat(productDTO).isEqualTo(storedProductDTO);
	}
	
	@Test
	@DisplayName("상품목록")
	@Disabled
	void productList() {
		List<ProductDTO> list = productDAOImpl.productList();
		for(ProductDTO item : list) {
			log.info("item.toString()");
		}
		
	}
}
