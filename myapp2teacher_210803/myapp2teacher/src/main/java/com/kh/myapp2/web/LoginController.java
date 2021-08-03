package com.kh.myapp2.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.myapp2.web.form.LoginForm;
import com.kh.myapp2.web.form.LoginMember;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	/**
	 * 로그인양식
	 * @return
	 */
	@GetMapping("/login")
	public String loginForm(@ModelAttribute LoginForm loginForm ) {
		return "login/loginForm";
	}
	
	/**
	 * 로그인처리
	 * @param loginForm
	 * @param bindingResult
	 * @param model
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute LoginForm loginForm,	
			BindingResult bindingResult,
			Model model, HttpServletRequest request) {
		
		log.info("loginForm:{}", loginForm);
		String mID = "test";
		String mPW = "1234";
		
		LoginMember loginMember = null;
		//관리자인경우
		if(loginForm.getId().equals("admin") && loginForm.getPw().equals("1234")) {
			loginMember = new LoginMember(loginForm.getId(), "관리자", true);
		}else {
			//로긴 실패시..
			if(!(mID.equals(loginForm.getId()) && mPW.equals(loginForm.getPw()))) {
				bindingResult.reject("login", "아이디 또는 비밀번호가 잘못되었습니다");
				return "login/loginForm";
			}
		//일반회원인경우	
			loginMember = new LoginMember(loginForm.getId(), "일반회원", false);
		}
		
		//로긴성공
		HttpSession session =request.getSession(true);
		session.setAttribute("loginMember", loginMember );
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			log.info("세션제거!");
			session.invalidate();
		}
		
		return "redirect:/";
	}
}











