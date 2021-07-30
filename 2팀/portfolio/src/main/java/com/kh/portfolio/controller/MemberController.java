package com.kh.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.portfolio.member.DTO.MemberDTO;
import com.kh.portfolio.member.SVC.MemberSVC;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberSVC memberSVC;
	
	/**
	 * 로그인
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		
		return "member/login";
	}
	@PostMapping("/login")
	public String loginPost() {
		
		return "home";
	}
	
	/**
	 * 회원가입 정보입력
	 */
	@GetMapping("/joinForm")
	public String joinForm() {
		
		return "member/joinForm";
	}
	
	@PostMapping("")
	public String product3(
			@ModelAttribute MemberDTO memberDTO,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		log.info("Mid:{},Mname:{}, Mgender:{},Mtel:{}",
				memberDTO.getMid(),
				memberDTO.getMname(),
				memberDTO.getMgender(),
				memberDTO.getMtel());
//		model.addAttribute("productDTO", productDTO);
		
		MemberDTO storedMemberDTO = memberSVC.createMember(memberDTO);
		model.addAttribute("memberDTO", storedMemberDTO);
		redirectAttributes.addAttribute("Mid", storedMemberDTO.getMid());
		
		log.info("저장후-memberDTO:{}",memberDTO.toString());
		return "home";
	}
}