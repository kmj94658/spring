package com.kh;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kh.gongiapp.dto.GongiDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class InsertTest {
	 
	@Autowired
	private JdbcTemplate jt;
	
	@Test
	@DisplayName("공지 등록")
	void create() {
		String writer;
		String title;
		String content;
		
		GongiDTO gongiDTO = new GongiDTO();
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into gongi(num,writer,title,content) ");
		sql.append(" values('공지사항.'||gongi_num_seq.nextval,?,?,?) ");
		jt.update(sql.toString(),
							gongiDTO.getWriter(),
							gongiDTO.getTitle(),
							gongiDTO.getContent());
	}
}
