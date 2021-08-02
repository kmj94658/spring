package com.kh.myapp2.web.form;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.Data;

@Data //getter setter toString 자동생성
public class MemberForm {
	
	@ModelAttribute("hobby")
	public Map<String, String> hobby() {
		Map<String, String> hobby = new HashMap<>();
		
		hobby.put("01", "music");
		hobby.put("02", "movie");
		hobby.put("03", "golf");
		
		return hobby; //맵을 반환 (뷰에서는 hobby이름으로 접근 가능. 컨트롤러에 써두면 뷰단에서 참조가능.)
	}
	
	@NotBlank
	@Email
	private String id;					//회원아이디
	
	@NotBlank
	@Size(min=4, max=10)
	private String pw;					//회원비밀번호
	@NotBlank
	private String pwChk;				//비밀번호 확인
	@Size(min=2, message="취미는 2개 이상")
	private List<String> hobby;	//취미
	private Gender gender;			//성별(정해진 상수는 enum타입으로 받는다)
	private String region;			//지역
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	private LocalDate birth;		//생년월일
}
