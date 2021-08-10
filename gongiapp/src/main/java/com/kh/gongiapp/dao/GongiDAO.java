package com.kh.gongiapp.dao;

import java.util.List;

import com.kh.gongiapp.dto.GongiDTO;

public interface GongiDAO {
	
	/**
	 * 공지 등록
	 * @param gongiDTO
	 * @return
	 */
	GongiDTO createGongi(GongiDTO gongiDTO);
	
	/**
	 * 공지 조회
	 * @param num
	 * @return
	 */
	GongiDTO findGongi(String num);
	
	/**
	 * 공지 수정
	 * @param num
	 * @param gongiDTO
	 * @return
	 */
	GongiDTO editGongi(String num, GongiDTO gongiDTO); //수정해서 수정한 결과를 반환할수 있도록
	
	/**
	 * 공지 삭제
	 * @param num
	 */
	void deleteGongi(String num);
	
	/**
	 * 공지 목록
	 * @return
	 */
	List<GongiDTO> GongiList();
}
