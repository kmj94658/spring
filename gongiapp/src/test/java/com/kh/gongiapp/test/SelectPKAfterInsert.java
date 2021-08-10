package com.kh.gongiapp.test;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SelectPKAfterInsert {
	
	@Autowired
	private JdbcTemplate jt;
	
	@Test
	@DisplayName("insert후 자동생성된 pk가져오기")
	void getPK() {
		
		String sql = "insert into gongi(num,writer,title,content) values(('공지사항.'||gongi_num_seq.nextval),?,?,?) ";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jt.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"num"});
				pstmt.setString(1, "작성자1");
				pstmt.setString(2, "제목1");
				pstmt.setString(3, "공지사항 어플리케이션 내용입니다1111111");
				
				return pstmt;
			}
		}, keyHolder); //키홀더메소드 쓰기
		
		log.info("keyHolder:{}",keyHolder.getKeys().get("num"));
	}
}
