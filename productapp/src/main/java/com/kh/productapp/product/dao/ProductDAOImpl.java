package com.kh.productapp.product.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.productapp.product.dto.ProductDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {
	
	private final JdbcTemplate jdbcTemplate;
	
	/**
	 * 상품등록
	 */
	@Override
	public int addProduct(ProductDTO productDTO) {
		String sql = "insert into product values(?,?,?,?)";
		
		int result = jdbcTemplate.update(	sql,
																			productDTO.getId(),
																			productDTO.getName(),
																			productDTO.getStock(),
																			productDTO.getPrice());
		return result;
	}
	
	/**
	 * 상품수정
	 */
	@Override
	public int modifyProduct(ProductDTO productDTO) {
		StringBuffer sql = new StringBuffer();
		sql.append("update product ");
		sql.append(" set name = ? , ");
		sql.append(" stock = ? , ");
		sql.append(" price = ? ");
		sql.append(" where id = ? ");
		
		int rows = jdbcTemplate.update(
								sql.toString(),
								productDTO.getName(),
								productDTO.getStock(),
								productDTO.getPrice(),
								productDTO.getId());
		return rows;
	}
	
	/**
	 * 상품삭제
	 */
	@Override
	public int deleteProduct(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from product ");
		sql.append("where id = ? ");
		
		int rows = jdbcTemplate.update(sql.toString(), id);
		return rows;
	}
	
	/**
	 * 상품목록
	 */
	@Override
	public List<ProductDTO> productList() {
		StringBuffer sql = new StringBuffer();
		sql.append("select id, name, stock, price ");
		sql.append(" from product ");
		List<ProductDTO> list = jdbcTemplate.query(	sql.toString(), 
																								new BeanPropertyRowMapper<>(ProductDTO.class));
		return list;
	}

	/**
	 * 상품조회
	 */
	@Override
	public ProductDTO searchProduct(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id, name, stock, price ");
		sql.append(" from product ");
		sql.append(" where id = ? ");
		
		ProductDTO productDTO = jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(ProductDTO.class),id);
		return productDTO;
	}
}
