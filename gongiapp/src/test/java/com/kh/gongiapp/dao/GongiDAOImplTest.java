package com.kh.gongiapp.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kh.gongiapp.dto.GongiDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class GongiDAOImplTest {
	 
	@Autowired
	private GongiDAO gongiDAO;
	
	@Test
	@DisplayName("공지 등록")
	void create() {

	}
	
	@Test
	@DisplayName("공지 조회")
	void find() {
		GongiDTO gongiDTO = gongiDAO.findGongi("공지사항.84");
		log.info("GongiDTO:{}",gongiDTO);
	}
}
