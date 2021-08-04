package com.kh.myapp2.jdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SelectTest {

	@Autowired
	private JdbcTemplate jt;
	
	private StringBuffer sql;
	
	@BeforeEach //메소드실행전 1회수행
	void common() {
		log.info("common수행됨");
		sql = new StringBuffer();
	}
	
	@Test
	@DisplayName("단일레코드 조회 : 자동매핑")
	void singleRecordAuto() {

		String id = "STD001";
		sql.append("select id,name,kor,eng,math from student ");
		sql.append(" where id=? ");
		
		StudentDTO studentDTO = 
				jt.queryForObject(sql.toString(), 
													new BeanPropertyRowMapper<>(StudentDTO.class), 
													id);
		
		log.info("자동 매핑 StudentDTO:{}",studentDTO);
		
	}
	@Test
	@DisplayName("단일레코드 조회 : 수동매핑")
	void singleRecordManual() {

		String id = "STD001";
		sql.append("select id,name,kor,eng,math from student ");
		sql.append(" where id=? ");
		
		
		RowMapper<StudentDTO> rowMapper =	(rs, rowNum)->{
				StudentDTO studentDTO = new StudentDTO();
				
				studentDTO.setId(rs.getString("id"));
				studentDTO.setName(rs.getString("name"));
				studentDTO.setKor(rs.getInt("kor"));
				studentDTO.setEng(rs.getInt("eng"));
				studentDTO.setMath(rs.getInt("math"));
				
				return studentDTO;														
		};		
		
		StudentDTO studentDTO = 
				jt.queryForObject(sql.toString(), 
//													new RowMapper<StudentDTO>() {
//
//														@Override
//														public StudentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//															StudentDTO studentDTO = new StudentDTO();
//																														
//															studentDTO.setId(rs.getString("id"));
//															studentDTO.setName(rs.getString("name"));
//															studentDTO.setKor(rs.getInt("kor"));
//															studentDTO.setEng(rs.getInt("eng"));
//															studentDTO.setMath(rs.getInt("math"));
//															
//															return studentDTO;
//														}
//													}, 	
													
														(rs, rowNum)->{
															StudentDTO studentDTO2 = new StudentDTO();
															
															studentDTO2.setId(rs.getString("id"));
															studentDTO2.setName(rs.getString("name"));
															studentDTO2.setKor(rs.getInt("kor"));
															studentDTO2.setEng(rs.getInt("eng"));
															studentDTO2.setMath(rs.getInt("math"));
															
															return studentDTO2;														
														}						
														, id);
		
		log.info("수동매핑 StudentDTO:{}",studentDTO);		
	}
	@Test
	@DisplayName("단일값 조회")
	void singleValueAuto() {
		
		sql.append("select round(avg(kor),2) as average ");
		sql.append("  FROM student where id > ? ");
		
		Float result = jt.queryForObject(sql.toString(), Float.class, "STD010");
		
		log.info("국어 평균:{}",result);
		
	}
	@Test
	@DisplayName("다중레코드 조회 : 자동매핑")
	void multiRecordAuto() {
		
		int kor = 80;
		sql.append("select id,name,kor,eng,math ");
		sql.append("  from student ");
		sql.append(" where kor >= ? ");
		
		List<StudentDTO> list = jt.query(sql.toString(), 
				new BeanPropertyRowMapper<>(StudentDTO.class), kor);
		
		log.info("국어점수가 {} 이상인 학생정보",kor);
//		for(StudentDTO sdto : list) {
//			log.info(sdto.toString());
//		}
		list.stream().forEach(ele->log.info(ele.toString()));
		
	}
	@Test
	@DisplayName("다중레코드 조회 : 수동매핑")
	void multiRecordManual() {
		
		int kor = 80;
		sql.append("select id,name,kor,eng,math ");
		sql.append("  from student ");
		sql.append(" where kor >= ? ");
		
		List<StudentDTO> list = jt.query(
				sql.toString(), 
					(rs,rowNum) -> {
						StudentDTO studentDTO2 = new StudentDTO();
						
						studentDTO2.setId(rs.getString("id"));
						studentDTO2.setName(rs.getString("name"));
						studentDTO2.setKor(rs.getInt("kor"));
						studentDTO2.setEng(rs.getInt("eng"));
						studentDTO2.setMath(rs.getInt("math"));
						
						return studentDTO2;		
					}
				, kor);
		list.stream().forEach(ele->log.info(ele.toString()));
	}	
}





