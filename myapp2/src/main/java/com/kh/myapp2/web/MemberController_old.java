package com.kh.myapp2.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	public String joinForm(@ModelAttribute MemberForm memberForm) { //모델어트리뷰트로 validation 틀렸을때 입력하던 회원정보 다시 불러오기
		return "/member/joinForm";
	}
	
	//@GetMapping("/join")
	public String joinForm2(Model model) {
		model.addAttribute("mem", new MemberForm()); 
		return "member/joinForm";
	}
	
	//@GetMapping("/join")
	public String joinForm3(@ModelAttribute("mem") MemberForm memberForm) { 
		return "/member/joinForm";
	}
	
	/**
	 * 회원가입처리
	 * @return
	 */
	@PostMapping("/join")
	public String join(
			@Valid //폼객체, 커맨트객체 내에서 어노테이션 검증하고자 할때 사용
			@ModelAttribute MemberForm memberForm, //DTO와 같은 개념이지만 화면에서의 구별 위해 form 사용
																						 //modelAttribute은 1.view 데이터를 개체에 바인딩할때 사용 2. 모델 객체에 객체이름의 첫글자를 소문자로 변환한 이름으로 저장된다
			BindingResult bindingResult) {         //view 데이터를 바인딩할때 오류정보를 담고있는 객체
		
		log.info("memberForm:"+memberForm); //memberForm이 toString으로 인해 내부적으로 문자열 받아옴
		
		//비밀번호, 비밀번호확인 필드가 같은지 체크
		if(!memberForm.getPw().equals(memberForm.getPwChk())) {
			bindingResult.rejectValue("pwChk", "pwChk", "비밀번호가 일치하지 않습니다."); //필드레벨 리젝트
		}
		//성별,지역,생년월일 중 한가지는 반드시 입력해야 하는 조건 검증
		boolean flag = false;
//		if(memberForm.getGender() != null || memberForm.getRegion() != null || memberForm.getBirth() != null) {
//			flag = true;
//		}
		log.info("flag:{}",flag);
		if(!flag) { //flag가 참이 아니면
			bindingResult.reject("global", "성별, 지역, 생년월일 중 한가지는 입력해야합니다.");
			bindingResult.reject("global", "성별, 지역, 생년월일 중 한가지는 입력해야합니다222."); //글로벌레벨 리젝트
		}
		
		//에러 났을때 다시 돌아가기
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
