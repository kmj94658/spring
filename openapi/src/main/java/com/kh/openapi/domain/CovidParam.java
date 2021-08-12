package com.kh.openapi.domain;

import lombok.Data;

@Data
public class CovidParam {

	private String pageNo;				//페이지번호
	private String numOfRows;			//페이지당 레코드 수
	private String startCreateDt;	//시작일자
	private String endCreateDt;		//종료일자
}
