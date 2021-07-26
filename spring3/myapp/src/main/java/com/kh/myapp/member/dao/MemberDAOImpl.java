package com.kh.myapp.member.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.myapp.member.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO {
	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public int joinMember(MemberDTO memberDTO) {
		String sql = "insert into member values(?,?,?)";
		int result = jdbcTemplate.update(sql,memberDTO.getId(),memberDTO.getPwd(),memberDTO.getNickname());
		return result;
	}
}
