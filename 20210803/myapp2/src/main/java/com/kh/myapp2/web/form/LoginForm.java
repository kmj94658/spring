package com.kh.myapp2.web.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginForm {
	
	@NotBlank
	@Email
	@Size(min=4, max=10)
	private String id;
	
	@NotBlank
	private String pw;
	
}
