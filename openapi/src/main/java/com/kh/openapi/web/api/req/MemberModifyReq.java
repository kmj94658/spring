package com.kh.openapi.web.api.req;

import lombok.Data;

@Data
public class MemberModifyReq {

	private String id;
	private String pw;
	private String name;
}
