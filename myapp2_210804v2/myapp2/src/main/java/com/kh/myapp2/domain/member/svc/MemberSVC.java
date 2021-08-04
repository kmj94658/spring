package com.kh.myapp2.domain.member.svc;

import java.util.List;

import com.kh.myapp2.domain.member.dto.MemberDTO;

public interface MemberSVC {
	//회원가입
	MemberDTO join(MemberDTO memberDTO);
	
	//회원조회 by ID
	MemberDTO findByID(String id);
	
	//회원수정 by ID
	MemberDTO update(String id,MemberDTO memberDTO);
	
	//회원탈퇴 by ID
	void out(String id);
	
	//회원목록 
	List<MemberDTO> list();
	
	//로그인
	MemberDTO findByIdPw(String id,String pw);
	
	//아이디찾기
	String findId(String tel, String birth);
	
	//비밀번호찾기
	String findPw(String id, String tel, String birth);
}
