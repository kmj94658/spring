package com.kh.myapp2.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.myapp2.web.form.Code;
import com.kh.myapp2.web.form.Gender;
import com.kh.myapp2.web.form.MemberForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

	@ModelAttribute("hobbies")
	public Map<String,String> hobby() {
		Map<String,String> hobbies = new LinkedHashMap<>();
		
		hobbies.put("01","음악");
		hobbies.put("02","영화");
		hobbies.put("03","골프");
		
		//model.addAttribute("hobby",hobby);
		return hobbies;
	}
	
	@ModelAttribute("gender")
	public Gender[] gender() {
		
		return Gender.values();
	}
	
	@ModelAttribute("region")
	public List<Code> region(){
		List<Code> list = new ArrayList<>();
		list.add(new Code("01","서울"));
		list.add(new Code("02","울산"));
		list.add(new Code("03","부산"));
		list.add(new Code("04","제주"));
		return list;
	}
	
	
	
	/**
	 * 회원가입양식
	 * @return
	 */
	@GetMapping("/join")
	public String joinForm(
			@ModelAttribute MemberForm memberForm, Model model,
			HttpServletRequest request) {
//		model.addAttribute("memberForm",memberForm);
		
		HttpSession session = request.getSession(false);
		//세션 존재우무
		if(session != null) {
			log.info("session:{}",session.toString());
			session.getAttributeNames().asIterator()
			       .forEachRemaining(ele->{
			      	 log.info("session name:{}, value:{}",ele,session.getAttribute(ele));
			       });
			
			log.info("sessionId:{}",session.getId());
			log.info("maxInactiveInterval:{}",session.getMaxInactiveInterval());
			log.info("creationTime:{}",session.getCreationTime());
			log.info("lastAccessedTime:{}",new Date(session.getLastAccessedTime()));
			log.info("isNew:{}", session.isNew());
			
			return "redirect:/";
		}
		
		return "member/joinForm";
	}

	
	/**
	 * 회원가입처리
	 * @return
	 */
	@PostMapping("/join")
	public String join(
			@Valid //폼객체,커맨드객체내에서 어노테이션 검증하고자할때 사용
			//@ModelAttribute  1.view 데이터를 개체에 바인딩할때 사용 
			//                 2.model객체에 객체이름의 첫글자를 소문자로 변환한 이름으로 저장된다.
			//                   ex)model.addAttribute("memberForm",memberForm)
			@ModelAttribute MemberForm memberForm, 
			BindingResult bindingResult //view데이터를 바인딩할때 오류정보를 담고있는 객체 
			) {
		
		log.info("memberForm:"+memberForm);
		
		//비밀번호필드 ,비빌밀번호확인필드 체크
		if(!memberForm.getPw().equals(memberForm.getPwChk())) {
			bindingResult.rejectValue("pwChk","pwChk", "비밀번호가 맞지 않습니다");
		}
		
		//성별,지역,생년월일중 한가지는 입력해야 하는조건 검증
		boolean flag = false;
//		if(memberForm.getGender().getName().length() > 1|| 
//			 memberForm.getRegion().trim().length() > 1 || 
//			 memberForm.getBirth().lengthOfYear() > 1) {
//			flag =true;
//		}
		log.info("flag:{}",flag);
		if(!flag) {
			bindingResult.reject("aaa", "성별,지역,생년월일중 한가지는 입력해야합니다.");
		}
		
		
		if(bindingResult.hasErrors()) {
			log.info("errors={}",bindingResult);
			return "member/joinForm";
		}
		
		//정상로직
		
		//return "redirect:/members/{id}";
		return "home";
	}

	/**
	 * 회원조회
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public String view(@PathVariable("id") String id) {
		
		return "member/view";
	}
}













