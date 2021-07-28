package com.kh.productapp.product.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.kh.productapp.product.dto.ProductDTO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional //테스트환경에서 사용되면 테스트메소드 실행뒤에는 롤백된다.
@Slf4j
public class ProductDAOImplTEST {

//	@Autowired
//	private JdbcTemplate jt;
	@Autowired
	private ProductDAO productDAOImpl;
	
	@Test
	@DisplayName("상품등록")
	@Disabled
	void createProduct() {
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setPid("pro-07");
		productDTO.setPname("책상3");
		productDTO.setPcount(15);
		productDTO.setPprice(500000);
		
		ProductDTO storedProductDTO = productDAOImpl.createProduct(productDTO);
		
		//Assertions.assertThat(productDTO).isEqualToComparingFieldByField(storedProductDTO);
		//객체값비교 해서 같으면 true
		Assertions.assertThat(productDTO)
							.usingRecursiveComparison()
							.isEqualTo(storedProductDTO);
	}
	
	@Test
	@DisplayName("상품목록")
	@Disabled
	void findAll() {
		
		List<ProductDTO> list = productDAOImpl.findAll();
		
//		for(ProductDTO item : list) {
//			log.info(item.toString());
//		}
		Assertions.assertThat(list.size()).isEqualTo(10);
	}
	
	@Test
	@DisplayName("상품수정")
	void modifyProduct() {
		
		String pid = "pro-11";
		ProductDTO productDTO = new ProductDTO(pid, "의자수정후", 100, 150000);
		
		ProductDTO modfiedProductDTO = productDAOImpl.modifyProduct(pid, productDTO);
		Assertions.assertThat(productDTO)
							.usingRecursiveComparison()
							.isEqualTo(modfiedProductDTO);
	}
	
	@Test
	@DisplayName("상품삭제")
	void deleteProduct() {
		
		String pid = "pro-11";
		productDAOImpl.deleteProduct(pid);
		
		//ProductDTO productDTO = productDAOImpl.findProduct(pid);
		
		assertThrows(
				EmptyResultDataAccessException.class, 
				()->productDAOImpl.findProduct(pid),
				"Product is null"
		);
		
	}
}











