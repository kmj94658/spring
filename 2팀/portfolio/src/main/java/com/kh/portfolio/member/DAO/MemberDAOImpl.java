package com.kh.portfolio.member.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.portfolio.member.DTO.MemberDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO {
	
	private final JdbcTemplate jdbcTemplate;

	/**
	 * 회원가입
	 */
	@Override
	public MemberDTO createMember(MemberDTO memberDTO) {
			
		StringBuffer sql = new StringBuffer();
		sql.append("insert into member2 values(?,?,?,?,?,?,?,?,?,?) ");
		
		jdbcTemplate.update(sql.toString(), 
				memberDTO.getMid() ,memberDTO.getMpw(), 
				memberDTO.getMquestion(),memberDTO.getManswer(),
				memberDTO.getMname(), memberDTO.getMbirth(),
				memberDTO.getMgender(), memberDTO.getMtel(),
				memberDTO.getMaddress(), memberDTO.getMnickname());	
		
		return memberDTO;
	}

}
