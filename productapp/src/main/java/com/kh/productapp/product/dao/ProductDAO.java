package com.kh.productapp.product.dao;

import java.util.List;

import com.kh.productapp.product.dto.ProductDTO;

public interface ProductDAO {
	/**
	 * 상품 등록
	 * @param productDTO
	 * @return
	 */
	int addProduct(ProductDTO productDTO);
	
	/**
	 * 상품 수정
	 * @param productDTO
	 * @return
	 */
	int modifyProduct(ProductDTO productDTO);
	
	/**
	 * 상품 조회
	 * @param id
	 * @return
	 */
	ProductDTO searchProduct(String id);
	
	/**
	 * 상품 삭제
	 * @param id
	 * @return
	 */
	int deleteProduct(String id);
	
	/**
	 * 상품 전체조회
	 * @return
	 */
	List<ProductDTO> productList();
}
