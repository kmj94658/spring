package com.kh.myapp2.web;

import java.awt.Window;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.myapp2.web.form.LoginForm;
import com.kh.myapp2.web.form.LoginMember;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	/**
	 * 로그인 양식
	 * @return
	 */
	@GetMapping("/login")
	public String loginForm(@ModelAttribute LoginForm loginForm) {
		return "login/loginForm"; 
	}
	
	/**
	 * 로그인 처리
	 * @param id
	 * @param pw
	 * @return
	 */
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
		log.info("loginForm:{}", loginForm);
		String mID = "test";
		String mPW = "1234";
		
		
		
		LoginMember loginMember = new LoginMember(loginForm.getId(), "관리자", true);
		
		//관리자인 경우
		if(loginForm.getId().equals("admin") && loginForm.getPw().equals("1234")) {
			loginMember = new LoginMember(loginForm.getId(), "관리자", true);
		}else {
			//로그인 실패시
			if(!(mID.equals(loginForm.getId()) && mPW.equals(loginForm.getPw()))) {
				bindingResult.reject("login", "아이디 또는 비밀번호가 올바르지 않습니다");
				//model.addAttribute("msg","아이디 또는 비밀번호가 올바르지 않습니다");
				return "login/loginForm";
			}
			//일반회원인 경우
			loginMember = new LoginMember(loginForm.getId(),"일반회원", false);
		}
		
		//로그인 성공시
		HttpSession session = request.getSession(true); //있으면 있는 세션, 없으면 새 세션
		session.setAttribute("loginMember", loginMember);
		session.setMaxInactiveInterval(20);
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
}
