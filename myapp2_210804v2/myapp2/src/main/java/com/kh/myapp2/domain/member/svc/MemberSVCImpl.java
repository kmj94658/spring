package com.kh.myapp2.domain.member.svc;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.myapp2.domain.member.dao.MemberDAO;
import com.kh.myapp2.domain.member.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberSVCImpl implements MemberSVC {

	private final MemberDAO memberDAO;
	
	/**
	 * 회원가입
	 */
	@Override
	public MemberDTO join(MemberDTO memberDTO) {
		memberDAO.join(memberDTO);
		return memberDAO.findByID(memberDTO.getId());
	}

	@Override
	public MemberDTO findByID(String id) {
		
		return memberDAO.findByID(id);
	}

	@Override
	public MemberDTO update(String id, MemberDTO memberDTO) {
		memberDAO.update(id, memberDTO);
		return memberDAO.findByID(memberDTO.getId());
	}

	@Override
	public void out(String id) {
		memberDAO.out(id);
	}

	@Override
	public List<MemberDTO> list() {

		return memberDAO.list();
	}

	@Override
	public MemberDTO findByIdPw(String id, String pw) {

		return memberDAO.findByIdPw(id, pw);
	}

	@Override
	public String findId(String tel, String birth) {

		return memberDAO.findId(tel, birth);
	}

	@Override
	public String findPw(String id, String tel, String birth) {
		return memberDAO.findPw(id, tel, birth);
	}

}
