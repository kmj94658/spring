package com.kh.portfolio.member.SVC;

import com.kh.portfolio.member.DTO.MemberDTO;

public interface MemberSVC {
	
		/**
		 * 회원가입
		 * @param memberDTO
		 * @return
		 */
		MemberDTO createMember(MemberDTO memberDTO);
}
