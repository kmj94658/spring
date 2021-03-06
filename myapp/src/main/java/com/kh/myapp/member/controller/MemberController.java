package com.kh.myapp.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		log.info("String joinForm() 호출됨!");
		
		return "member/joinForm";
	}
	
	//회원가입 처리
	//@PostMapping("/join")
	public String join(
			@RequestParam("id") String id,
			@RequestParam("pwd") String pwd,
			@RequestParam("nickname") String nickname) {
		log.info("String join() 호출됨!");
//		log.info("id:{}",id);
//		log.info("pwd:{}",pwd);
//		log.info("nickname:{}",nickname);
		log.info("id:{}, pwd:{}, nickname:{}",id,pwd,nickname);
		
		return "redirect:/";
	}
	@PostMapping("/join")
	public String join2(MemberDTO memberDTO) {
		log.info("String join2() 호출됨!");
		log.info("id:{}, pwd:{}, nickname:{}",
				memberDTO.getId(),memberDTO.getPwd(),memberDTO.getNickname());
		
		memberSVC.joinMember(memberDTO);
		
		return "redirect:/";
	}
	@PostMapping("/modify")
	@ResponseBody
	public String modify(MemberDTO memberDTO) {
		String msg = "";
		int result = memberSVC.modifyMember(memberDTO);
		msg = (result ==1) ? "ok" : "nok";
		return msg;
	}
	@GetMapping("/one")
	@ResponseBody
	public String one(@RequestParam("id") String id) {
		MemberDTO memberDTO = memberSVC.searchMember(id);
		
		return memberDTO.toString();
	}
	/**
	 * 회원삭제
	 * @param id
	 * @return
	 */
	@GetMapping("/del")
	public String del(@RequestParam("id") String id) {
		int result = memberSVC.deleteMember(id);
		msg = (result ==1) ? "ok" : "nok";
		return msg;
	}
	@GetMapping("/all")
	@ResponseBody
	public String all() {
		List<MemberDTO> list = memberSVC.memberList();
		return list.toString();
	}
}
