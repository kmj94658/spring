package com.kh.gongiapp.svc;

import java.util.List;

import com.kh.gongiapp.dto.GongiDTO;

public interface GongiSVC {
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
	GongiDTO editGongi(String num, GongiDTO gongiDTO);
	
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
