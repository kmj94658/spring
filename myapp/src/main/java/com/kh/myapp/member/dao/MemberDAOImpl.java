package com.kh.myapp.member.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.myapp.member.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor //멤버필드가 final인 필드를 매개변수로 하는 생성자를 자동 만들어준다
public class MemberDAOImpl implements MemberDAO {

	private final JdbcTemplate jdbcTemplate;
	
	/**
	 * 회원가입
	 */
	@Override
	public int joinMember(MemberDTO memberDTO) {
		log.info("int joinMember(MemberDTO memberDTO) 호출됨!");
		log.info("id:{}, pwd:{}, nickname:{}",
				memberDTO.getId(),memberDTO.getPwd(),memberDTO.getNickname());
		
		
		String sql = "insert into member values(?,?,?)";
		
		int result = jdbcTemplate.update(sql,
																			memberDTO.getId(),
																			memberDTO.getPwd(),
																			memberDTO.getNickname());
		return result;
	}	

	/**
	 * 회원수정
	 * @param id
	 * @return
	 */
	@Override
	public int modifyMember(MemberDTO memberDTO) {
		StringBuffer sql = new StringBuffer();
		sql.append("update member ");
		sql.append("set pwd = ?, ");
		sql.append("nickname = ? ");
		sql.append("where id = ? ");
		
		int rows = jdbcTemplate.update(
												sql.toString(), 
												memberDTO.getPwd(),
												memberDTO.getNickname(), 
												memberDTO.getId());
		return rows;
	}
	
	/**
	 * 회원조회 by id
	 * @param id
	 */
	@Override
	public MemberDTO searchMember(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,pwd,nickname ");
		sql.append(" from member ");
		sql.append(" where id = ? ; ");
		
		MemberDTO memberDTO = jdbcTemplate.queryForObject( //result test레코드가 1개일때 사용
				sql.toString(), 
				new BeanPropertyRowMapper<>(MemberDTO.class),
				id);
		
		return memberDTO;
	}
	
	/**
	 * 회원탈퇴
	 * @param id
	 * @return
	 */
	@Override
	public int deleteMember(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from member ");
		sql.append("where id = ? ");
		
		int rows = jdbcTemplate.update(sql.toString(), id);
		return rows;
	}
	
	/**
	 * 회원 전체조회
	 */
	@Override
	public List<MemberDTO> memberList() {
		String sql = "select id,pwd,nickname from member";
		
		List<MemberDTO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MemberDTO.class));
		return list;
	}

}
