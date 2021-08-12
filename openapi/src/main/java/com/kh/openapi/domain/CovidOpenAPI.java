package com.kh.openapi.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;

@Slf4j
@Component
public class CovidOpenAPI {

	private final static String SERVICE_KEY = "CiShXgJJ0EZk05haxZW1R%2BnUZY4ba2YHM4jhOr%2BPjrEg5tbsblX8gg5kevW3acMFJ2jD9c10AETHpeX9uzQmlQ%3D%3D";
	
	public String getCovid(CovidParam covidParam) throws IOException {
		 StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson"); /*URL*/
     
		 //요청파라미터
		 urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + SERVICE_KEY); /*Service Key*/
     urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
     urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(covidParam.getPageNo(), "UTF-8")); /*페이지번호*/
     urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(covidParam.getNumOfRows(), "UTF-8")); /*한 페이지 결과 수*/
     urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(covidParam.getStartCreateDt(), "UTF-8")); /*검색할 생성일 범위의 시작*/
     urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(covidParam.getEndCreateDt(), "UTF-8")); /*검색할 생성일 범위의 종료*/
     
     URL url = new URL(urlBuilder.toString());
     HttpURLConnection conn = (HttpURLConnection) url.openConnection();
     conn.setRequestMethod("GET");
     conn.setRequestProperty("Content-type", "application/json");
     log.info("Response code:{}",conn.getResponseCode());
     
     //응답메세지 바디 읽기
     BufferedReader rd = null;
     if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
     } else {
         rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
     }
     //읽어온 응답메시지 문자열객체로 변환
     StringBuilder sb = new StringBuilder();
     String line;
     while ((line = rd.readLine()) != null) {
         sb.append(line);
     }
     rd.close();
     conn.disconnect();
   
     //log.info(sb.toString());
     
     //xml to json
     int INDENT_FACTOR = 2;
     JSONObject xmlJSONObj = XML.toJSONObject(sb.toString()); 
     
     //들여쓰기
     String jsonPrettyString = xmlJSONObj.toString(INDENT_FACTOR);
     
     return jsonPrettyString;
	}
}
