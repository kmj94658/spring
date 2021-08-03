package com.kh.myapp2.web.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginMember {
	private String id;
	private String nickname;
	private boolean admin;
}
