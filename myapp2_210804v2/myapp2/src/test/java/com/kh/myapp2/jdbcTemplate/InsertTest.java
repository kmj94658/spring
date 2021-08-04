package com.kh.myapp2.jdbcTemplate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class InsertTest {

	@Autowired
	private JdbcTemplate jt;
	
	@Test
	@DisplayName("학생점수 등록")
	//@Transactional
	@RepeatedTest(10) //10회 반복수행
	@Disabled
	void insert() {
		
		int kor = genderteKey(60, 100);
		int eng = genderteKey(60, 100);
		int math = genderteKey(60, 100);
		StudentDTO studentDTO = new StudentDTO(null,"홍길동1", kor,eng,math);
	
		//String sql = "insert into student (id,name,kor,eng,math) values (?,?,?,?,?)";
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("insert into student (id,name,kor,eng,math)  ");
		sql.append("     values ( ('STD' ||substr('000' || student_id_seq.nextval,-3)) , ");
		sql.append("              ('홍길동' ||substr('000' || student_id_seq.currval,-3)) ");
		sql.append("              ,?,?,?) ");  		
		
		jt.update(sql.toString(), studentDTO.getKor(),studentDTO.getEng(),studentDTO.getMath());
		
		//Assertions.assertThat(studentDTO).isEqualTo(findById(studentDTO.getId()));
	}
	
	private int genderteKey(int from, int to) {
		return (int)(Math.random()*(to-from+1))+from;
	}
	
	private StudentDTO findById(String id) {
		
		String sql = "select id,name,kor,eng,math from student where id = ?";
		
		StudentDTO studentDTO = jt.queryForObject(sql, 
				new BeanPropertyRowMapper<>(StudentDTO.class),id) ;
		
		return studentDTO;
	}	
	
	@Test
	@DisplayName("학생정보 수정 by ID")
	void update() {
		
		StudentDTO studentDTO = new StudentDTO("STD001", "홍길동001", 100,100,100);
		
		StringBuffer sql = new StringBuffer();
		sql.append("update student ");
		sql.append("   set kor = ?, ");
		sql.append("       eng = ?, ");
		sql.append("       math = ? ");
		sql.append(" where id = ? ");		
		
		jt.update(sql.toString(), studentDTO.getKor(), 
				      studentDTO.getEng(), studentDTO.getMath(), studentDTO.getId());
		
		Assertions.assertThat(studentDTO).isEqualTo(findById(studentDTO.getId()));
	}
	
	
	@Test
	@DisplayName("학생정보 삭제 by ID")
	void delete() {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from student ");
		sql.append(" where id = (select max(id) from student) ");
		
		jt.update(sql.toString());
	}
	
//	@Test
//	@DisplayName("학생조회 by ID")
//	void findById() {
//		
//		String sql = "select id,name,kor,eng,math from student where id = ?";
//		
//		StudentDTO studentDTO = jt.queryForObject(sql, 
//				new BeanPropertyRowMapper<>(StudentDTO.class)) ;
//	}
}


