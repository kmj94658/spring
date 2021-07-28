package com.kh.productapp.product.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.productapp.product.dto.ProductDTO;

import lombok.RequiredArgsConstructor;

@Repository
//@AllArgsConstructor
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO{

	private final JdbcTemplate jdbcTemplate;
	
	///@Autowired
//	public ProductDAOImpl(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	
	/**
	 * 상품등록
	 */
	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into product values(?,?,?,?) ");
		
		jdbcTemplate.update(sql.toString(), 
				productDTO.getPid(), productDTO.getPname(), 
				productDTO.getPcount(),productDTO.getPprice());	
		
		return findProduct(productDTO.getPid());
	}

	/**
	 * 상품조회
	 */
	@Override
	public ProductDTO findProduct(String pid) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select pid,pname,pcount,pprice ");
		sql.append("  from product ");
		sql.append(" where pid = ? ");
		
		ProductDTO productDTO =	jdbcTemplate.queryForObject(
		  sql.toString(), 
			new BeanPropertyRowMapper<>(ProductDTO.class),
			pid
			);
		
		return productDTO;
	}
	
	/**
	 * 상품목록조회
	 */
	@Override
	public List<ProductDTO> findAll() {

		StringBuffer sql = new StringBuffer();
		sql.append("select pid, pname, pcount, pprice ");
		sql.append("  from product ");
		
		List<ProductDTO> list = jdbcTemplate.query(
			sql.toString(),
			new BeanPropertyRowMapper<>(ProductDTO.class)
		);
		
		return list;
	}

	/**
	 * 상품 수정
	 */
	@Override
	public ProductDTO modifyProduct(String pid, ProductDTO productDTO) {

		StringBuilder sql = new StringBuilder();
		sql.append("update product ");
		sql.append("   set pname = ?, ");
		sql.append("       pcount = ?, ");
		sql.append("       pprice = ? ");
		sql.append(" where pid = ? ");
		
		jdbcTemplate.update(
				sql.toString(), 
				productDTO.getPname(),
				productDTO.getPcount(),
				productDTO.getPprice(),pid);
		
		return findProduct(pid);
	}

	/**
	 * 상품 삭제
	 */
	@Override
	public void deleteProduct(String pid) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from product ");
		sql.append(" where pid = ? ");
		
		jdbcTemplate.update(sql.toString(), pid);
		
	}
}












