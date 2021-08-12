package com.kh.openapi.web;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

	@GetMapping("/all")
	public String list() {
		return "members/list";
	}
	
	@ResponseBody
	@PostMapping("")
	public String regist(@RequestBody Person person) {
		String result = "";
		log.info("person:{}",person.toString());
		
		if(person.getName().equals("홍길북")) {
			result = "ok";
		} else {
			result = "nok";
		}
		
		return result;
	}
}
