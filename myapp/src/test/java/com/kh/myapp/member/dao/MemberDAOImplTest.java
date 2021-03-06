package com.kh.myapp.member.dao;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kh.myapp.member.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberDAOImplTest {
	
	@Autowired
	private JdbcTemplate jt;
	MemberDAOImpl memberDAOImpl;
	
	@BeforeEach //test메소드 각각 실행 전에 1회 실행
	void init() {
		memberDAOImpl = new MemberDAOImpl(jt);
	}
	
	@Test
	@DisplayName("회원가입")
	//@Disabled
	@Order(1)
	void joinMember() {
		
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setId("test5@test.com");
		memberDTO.setPwd("1234");
		memberDTO.setNickname("홍길동5");
		
		int result = memberDAOImpl.joinMember(memberDTO);
		Assertions.assertThat(result).isEqualTo(1);
	}
	@Test
	@DisplayName("회원수정")
	@Disabled
	void modifyMember() {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("test@test.com");
		memberDTO.setPwd("1234");
		memberDTO.setNickname("테스터99");
		
		int result = memberDAOImpl.modifyMember(memberDTO);
		Assertions.assertThat(result).isEqualTo(1);
	}
	@Test
	@DisplayName("회원탈퇴")
	@Order(2)
	void deleteMember() {
		int result = memberDAOImpl.deleteMember("test5@test.com");
		Assertions.assertThat(result).isEqualTo(1);
	}
	@Test
	@DisplayName("회원조회")
	void searchMember() {
		MemberDTO expected = new MemberDTO();
		expected.setId("test4@test.com");
		expected.setPwd("1234");
		expected.setNickname("홍길동4");
		
		MemberDTO result = memberDAOImpl.searchMember("test4@test.com");
		
		Assertions.assertThat(expected).isEqualToComparingFieldByField(result);
	}
	@Test
	@DisplayName("회원전체조회")
	@Disabled
	void memberList() {
		
		List<MemberDTO> list = memberDAOImpl.memberList();
		for(MemberDTO mdto : list) {
		log.info(mdto.toString());
		}
	}
}
