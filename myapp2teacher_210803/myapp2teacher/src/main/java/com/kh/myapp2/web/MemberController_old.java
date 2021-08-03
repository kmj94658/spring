package com.kh.myapp2.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.myapp2.web.form.MemberForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
//@RequestMapping("/members")
public class MemberController_old {

	/**
	 * 회원가입양식
	 * @return
	 */
	//@GetMapping("/join")
	public String joinForm(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "member/joinForm";
	}
	//@GetMapping("/join")
	public String joinForm2(@ModelAttribute("mem") MemberForm memberForm) {
		return "member/joinForm";
	}
	//@GetMapping("/join")
	public String joinForm5(Model model) {
		model.addAttribute("mem", new MemberForm());
		return "member/joinForm";
	}
	@GetMapping("/join")
	public String joinForm3(@ModelAttribute MemberForm memberForm) {
		return "member/joinForm";
	}
	//@GetMapping("/join")
	public String joinForm4(MemberForm memberForm) {
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
			bindingResult.reject("bccc", "성별,지역,생년월일중 한가지는 입력해야합니다.2222");
			bindingResult.reject("zzzzz", "성별,지역,생년월일중 한가지는 입력해야합니다.55555");
		}
		
		
		if(bindingResult.hasErrors()) {
			log.info("errors={}",bindingResult);
			return "member/joinForm";
		}
		
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













