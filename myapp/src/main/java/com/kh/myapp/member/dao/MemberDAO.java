package com.kh.myapp.member.dao;

import java.util.List;

import com.kh.myapp.member.dto.MemberDTO;

public interface MemberDAO {
	/**
	 * 회원가입
	 * @param memberDTO
	 * @return
	 */
	int joinMember(MemberDTO memberDTO);
	
	/**
	 * 회원수정
	 * @param id
	 * @return
	 */
	int modifyMember(MemberDTO memberDTO);
	
	/**
	 * 회원조회 by id
	 * @param id
	 */
	MemberDTO searchMember(String id);
	
	/**
	 * 회원탈퇴
	 * @param id
	 * @return
	 */
	int deleteMember(String id);
	
	/**
	 * 회원 전체조회
	 */
	List<MemberDTO> memberList();
}
