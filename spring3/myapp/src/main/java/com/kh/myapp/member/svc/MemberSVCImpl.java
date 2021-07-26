package com.kh.myapp.member.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.myapp.member.dao.MemberDAO;
import com.kh.myapp.member.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberSVCImpl implements MemberSVC {
	@Autowired
	private final MemberDAO memberDAO;
	
	public MemberSVCImpl(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	//ȸ������
	@Override
	public int joinMember(MemberDTO memberDTO) {
		int result = 0;
		result = memberDAO.joinMember(memberDTO);
		return result;
	}
}
