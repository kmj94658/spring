package com.kh.gongiapp.svc;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.gongiapp.dao.GongiDAO;
import com.kh.gongiapp.dto.GongiDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GongiSVCImpl implements GongiSVC {

	private final GongiDAO gongiDAO;
	
	/**
	 * 공지 등록
	 */
	@Override
	public GongiDTO createGongi(GongiDTO gongiDTO) {
		gongiDAO.createGongi(gongiDTO);
		return gongiDAO.findGongi(gongiDTO.getNum());
	}
	
	/**
	 * 공지 조회
	 */
	@Override
	public GongiDTO findGongi(String num) {
		return gongiDAO.findGongi(num);
	}
	
	/**
	 * 공지 수정
	 */
	@Override
	public GongiDTO editGongi(String num, GongiDTO gongiDTO) {
		gongiDAO.editGongi(num, gongiDTO);
		return findGongi(gongiDTO.getNum());
	}

	/**
	 * 공지 삭제
	 */
	@Override
	public void deleteGongi(String num) {
		gongiDAO.deleteGongi(num);
	}
	
	/**
	 * 공지 목록
	 */
	@Override
	public List<GongiDTO> GongiList() {
		return gongiDAO.GongiList();
	}

}
