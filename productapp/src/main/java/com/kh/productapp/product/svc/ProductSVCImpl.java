package com.kh.productapp.product.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.productapp.product.dao.ProductDAO;
import com.kh.productapp.product.dto.ProductDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductSVCImpl implements ProductSVC {
	
	@Autowired
	private final ProductDAO productDAO;
	
	public ProductSVCImpl(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	/**
	 * 상품등록
	 */
	@Override
	public int addProduct(ProductDTO productDTO) {
		int result = 0;
		result = productDAO.addProduct(productDTO);
		return result;
	}
	
	/**
	 * 상품수정
	 */
	@Override
	public int modifyProduct(ProductDTO productDTO) {
		return productDAO.modifyProduct(productDTO);
	}
	
	/**
	 * 상품삭제
	 */
	@Override
	public int deleteProduct(String id) {
		return productDAO.deleteProduct(id);
	}
	
	/**
	 * 상품목록
	 */
	@Override
	public List<ProductDTO> productList() {
		return productDAO.productList();
	}

	/**
	 * 상품조회
	 */
	@Override
	public ProductDTO searchProduct(String id) {
		return productDAO.searchProduct(id);
	}
}
