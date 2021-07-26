package com.kh.myapp.member.dao;

import com.kh.myapp.member.dto.MemberDTO;

public interface MemberDAO {
	//회원가입
	int joinMember(MemberDTO memberDTO);
}
