package com.kh.myapp2.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.myapp2.web.form.LoginMember;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/products")
public class ProductController {

	/**
	 * 상품목록
	 * @return
	 */
	//@GetMapping("")
	public String products(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		LoginMember loginMember = 
				(LoginMember)session.getAttribute("loginMember");
		
		log.info("별칭:{}", loginMember.getNickname());
		return "product/list";
	}
	@GetMapping("")
	public String products2(
			@SessionAttribute(name="loginMember",required = false) LoginMember loginMember) {
//		HttpSession session = request.getSession(false);
//		
//		LoginMember loginMember = 
//				(LoginMember)session.getAttribute("loginMember");
		if(loginMember != null) {
			log.info("별칭:{}", loginMember.getNickname());
		}else {
			return "login/loginForm";
		}
		return "product/list";
	}
}







