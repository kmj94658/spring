package com.kh.myapp2.web.form;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberForm {
	//@NotBlank 문자열, @NotNull && 문자길이가 0보다 커야함. 
	//@NotEmpty 문자열,컬렉션  @NotNull && 문자길이가 0보다 커야함, 컬렉션은 요소가 1개이상
	//@NotNull 모든타입
	
	@NotBlank
	@Email
	private String id;			//아이디
	
	@NotBlank
	//@Size(min=4, max=10)
	@Pattern(regexp = "^\\d{4,10}$",message = "비밀번호는 숫자만 입력가능합니다." )
	private String pw;			//비밀번호
	
	@NotBlank
	private String pwChk;		//비밀번호체크
	@Size(min=2,message = "취미는2개이상!!")
	private List<String> hobby; //취미
	private Gender gender;	//성별
	private String region;	//지역
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	private LocalDate birth; //생년월일
	
	private boolean letterYN; //뉴스레터수신여부
}
