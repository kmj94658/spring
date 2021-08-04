package com.kh.myapp2.domain.member.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.myapp2.domain.member.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO {

	private final JdbcTemplate jt;
	
	@Override
	public void join(MemberDTO memberDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public MemberDTO findByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String id, MemberDTO memberDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void out(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MemberDTO> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberDTO findByIdPw(String id, String pw) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findId(String tel, String birth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findPw(String id, String tel, String birth) {
		// TODO Auto-generated method stub
		return null;
	}

}
