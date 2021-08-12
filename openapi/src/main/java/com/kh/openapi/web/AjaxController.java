package com.kh.openapi.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ajax")
public class AjaxController {

	@ResponseBody
	@GetMapping("/get/1")
	public Person get1() {
		
		Person p1 = new Person("홍길동",20);
		
		return p1;
	}
	
	@ResponseBody
	@GetMapping("/get/2")
	public Map<String,Person> get2() {
		
		Map<String, Person> map = new HashMap<>();
		
		map.put("one", new Person("홍길동1",10));
		map.put("two", new Person("홍길동2",20));
		map.put("three", new Person("홍길동3",30));
		
		return map;
	}
	
	@ResponseBody
	@GetMapping("/get/3")
	public List<Person> get3() {
		
		List<Person> list = new ArrayList<>();
		
		list.add(new Person("홍길동1",10));
		list.add(new Person("홍길동2",20));
		list.add(new Person("홍길동3",30));
		
		return list;
	}
	
	@ResponseBody
	@GetMapping("/get/4")
	public String get4() {
		return "hi";
	}
	
	@ResponseBody
	@GetMapping("/get/5")
	public String get5() {
		return "<html><head></head><body><h1>hi</h1></body></html>";
	}
	
	@ResponseBody
	@PostMapping("/post/1")
	public String post1(@RequestBody Person person) {
		
		log.info("/post/1 : {}", person.toString());
		
		String result = "";
		if(person.getName().equals("홍길순")) {
			result = "ok";
		} else {
			result = "nok";
		}
		return result;
	}
}
