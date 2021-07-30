package com.kh.portfolio.member.DAO;

import com.kh.portfolio.member.DTO.MemberDTO;

public interface MemberDAO {
	/**
	 * 회원가입
	 * @param memberDTO
	 * @return
	 */
	MemberDTO createMember(MemberDTO memberDTO);
}
