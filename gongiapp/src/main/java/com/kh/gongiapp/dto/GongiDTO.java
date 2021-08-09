package com.kh.gongiapp.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GongiDTO {
	 
	private String num;
	private String writer;
	private String title;
	private String content;
	private LocalDateTime wdate;
	private LocalDateTime udate;
}
