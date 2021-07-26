package com.kh.myapp.member.svc;

import com.kh.myapp.member.dto.MemberDTO;

public interface MemberSVC {
	//회원가입
	int joinMember(MemberDTO memberDTO);
	
}
