package com.kh.myapp2.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@GetMapping("")
	public String products(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		LoginMember loginMember = 
				(LoginMember)session.getAttribute("loginMember");
		
		log.info("별칭:{}", loginMember.getNickname());
		return "product/list";
	}
}







