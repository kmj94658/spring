package com.kh.openapi;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaToJsonTest {

	@Test
	void test1() throws JsonProcessingException {
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		Person person = new Person("홍길동",20);
		
		String json = objectMapper.writeValueAsString(person);
		log.info("obj=>json : {}",json);
		
		Person person2 = objectMapper.readValue(json, Person.class);
		log.info("json=>obj : {}", person2.toString());
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person{
	String name;
	int age;
}