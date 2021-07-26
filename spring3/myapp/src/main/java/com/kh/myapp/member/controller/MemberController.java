package com.kh.myapp.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.myapp.member.dto.MemberDTO;
import com.kh.myapp.member.svc.MemberSVC;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberSVC memberSVC;
	//회원가입 양식
	@GetMapping("/joinForm")
	public String joinForm() {
		return "member/joinForm";
	}
	
	//회원가입 처리
	@PostMapping("/join")
	public String join(MemberDTO memberDTO) {
		log.info("id:{}, pwd:{}, nickname:{}",memberDTO.getId(), memberDTO.getPwd(),memberDTO.getNickname());
		
		memberSVC.joinMember(memberDTO);
		return "redirect:/";
	}
}
