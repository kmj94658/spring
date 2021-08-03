package com.kh.myapp2.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import com.kh.myapp2.web.form.Code;
import com.kh.myapp2.web.form.Gender;
import com.kh.myapp2.web.form.MemberForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/members") //클래스레벨의 경로 표현
public class MemberController {
	
	@ModelAttribute("hobbies")
	public Map<String, String> hobby() {
		Map<String, String> hobbies = new LinkedHashMap<>();
		
		hobbies.put("01","음악");
		hobbies.put("02","영화");
		hobbies.put("03","골프");
		
		return hobbies; //맵을 반환 (뷰에서는 hobby이름으로 접근 가능. 컨트롤러에 써두면 뷰단에서 참조가능.)
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
	@GetMapping("/join") //클릭함으로써 get방식으로 전달
	public String joinForm(@ModelAttribute MemberForm memberForm, Model model, HttpServletRequest request) { //빈 memberForm객체가 만들어진다
		//model.addAttribute("memberForm", memberForm); 여기까지 만들어낸다
		
		HttpSession session = request.getSession(false);
		//세션 존재 유무 판단
			if(session != null) {
				log.info("session:{}",session.toString());
				session.getAttributeNames().asIterator().forEachRemaining(ele->{
					log.info("session name:{}, value:{}",ele,session.getAttribute(ele));
				});
				log.info("sessionId:{}",session.getId());
//				log.info("sessionId:{}",session.getId());
//				log.info("sessionId:{}",session.getId());
//				log.info("sessionId:{}",session.getId());
//				log.info("sessionId:{}",session.getId());
				
				return "redirect:/"; //세션이 있으면 홈으로
			}
			
		return "/member/joinForm"; //세션이 없으면 회원가입폼으로
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
			bindingResult.reject("global", "성별, 지역, 생년월일 중 한가지는 입력해야합니다222."); //오브젝트레벨 리젝트
		}
		
		//에러 났을때 회원가입으로 다시 돌아가기
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
