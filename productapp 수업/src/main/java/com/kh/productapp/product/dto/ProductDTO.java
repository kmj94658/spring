package com.kh.productapp.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class ProductDTO {

	private String pid;			//	PID	VARCHAR2(10 BYTE)
	private String pname;		//	PNAME	VARCHAR2(30 BYTE)
	private Integer pcount;	//	PCOUNT	NUMBER(3,0)
	private Integer pprice;	//	PPRICE	NUMBER(8,0)
	
}
