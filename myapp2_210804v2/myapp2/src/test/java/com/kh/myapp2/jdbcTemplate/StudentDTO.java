package com.kh.myapp2.jdbcTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //setter,getter,toString,equals,hasCode, final만 매개변수로 갖는 생성자.
@AllArgsConstructor //모든멤버를 매개변수로갖는 생성자
@NoArgsConstructor //디폴트생성자
public class StudentDTO {
	private String id;
	private String name;
	private int kor;
	private int eng;
	private int math;
}
