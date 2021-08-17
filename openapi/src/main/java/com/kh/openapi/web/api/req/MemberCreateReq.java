package com.kh.openapi.web.api.req;

import lombok.Data;

@Data
public class MemberCreateReq {

	private String id;
	private String pw;
	private String name;
}
