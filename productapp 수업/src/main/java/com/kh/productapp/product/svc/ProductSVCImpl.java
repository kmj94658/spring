package com.kh.productapp.product.svc;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.productapp.product.dao.ProductDAO;
import com.kh.productapp.product.dto.ProductDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC{
	
	private final ProductDAO productDAO;
	/**
	 * 상품등록
	 */
	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		
		return productDAO.createProduct(productDTO);
	}
	/**
	 * 상품조회
	 */
	@Override
	public ProductDTO findProduct(String pid) {
		
		return productDAO.findProduct(pid);
	}
	/**
	 * 상품목록
	 */
	@Override
	public List<ProductDTO> findAll() {
	
		return productDAO.findAll();
	}
	/**
	 * 상품 수정
	 */
	@Override
	public ProductDTO modifyProduct(String pid, ProductDTO productDTO) {
		return productDAO.modifyProduct(pid, productDTO);
	}
	/**
	 * 상품 삭제
	 */
	@Override
	public void deleteProduct(String pid) {		
		productDAO.deleteProduct(pid);
	}
}
