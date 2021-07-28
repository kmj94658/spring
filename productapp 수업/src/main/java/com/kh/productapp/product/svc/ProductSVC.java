package com.kh.productapp.product.svc;

import java.util.List;

import com.kh.productapp.product.dto.ProductDTO;

public interface ProductSVC {
	
	/**
	 * 상품등록
	 * @param productDTO
	 * @return 등록된상품
	 */
	ProductDTO createProduct(ProductDTO productDTO);
	
	/**
	 * 상품조회
	 * @param pid
	 * @return 조회된 상품
	 */
	ProductDTO findProduct(String pid);
	
	/**
	 * 상품목록
	 * @return 상품목록
	 */
	List<ProductDTO> findAll(); 
	
	/**
	 * 상품수정
	 * @param pid
	 * @param productDTO
	 * @return 수정된상품
	 */
	ProductDTO modifyProduct(String pid, ProductDTO productDTO);
	
	/**
	 * 상품삭제
	 * @param pid 상품아이디
	 */
	void deleteProduct(String pid);	
}
