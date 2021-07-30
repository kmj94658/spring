package com.kh.portfolio.member.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class MemberDTO {
	
	private String Mid;			// Mid        varchar2(50) PRIMARY key,
	private String Mpw; 		// Mpw       varchar2(50),
	private String Mquestion;	// Mquestion  varchar2(50),
	private String Manswer;		// Manswer  varchar2(50),
	private String Mname;		// Mname  varchar2(50),
	private String Mbirth;		// Mbirth      varchar2(50),
	private String Mgender;		// Mgender     varchar2(50),
	private String Mtel; 		// Mtel    varchar2(50),
	private String Maddress;	// Maddress   varchar2(50),
	private String Mnickname;	// Mnickname   varchar2(50),
}
