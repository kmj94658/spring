package com.kh.openapi.web;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.openapi.domain.CovidOpenAPI;
import com.kh.openapi.domain.CovidParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class CovidController {

	private final CovidOpenAPI covid;
	
	@ResponseBody //응답메세지로 리턴
	@GetMapping("/covid")
	public String covid(@ModelAttribute CovidParam covidParam) throws IOException {
		String covidXML = covid.getCovid(covidParam);
		return covidXML;
	}
}
