package com.kh.portfolio.member.SVC;

import org.springframework.stereotype.Service;

import com.kh.portfolio.member.DAO.MemberDAO;
import com.kh.portfolio.member.DTO.MemberDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberSVCImpl implements MemberSVC {

	private final MemberDAO memberDAO;
	
	@Override
	public MemberDTO createMember(MemberDTO memberDTO) {
		
		return memberDAO.createMember(memberDTO);
	}

}
