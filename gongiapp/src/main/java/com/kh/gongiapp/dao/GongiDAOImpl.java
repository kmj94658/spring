package com.kh.gongiapp.dao;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.gongiapp.dto.GongiDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GongiDAOImpl implements GongiDAO {

	private final JdbcTemplate jt;
	/**
	 * 공지 등록
	 */
	@Override
	public GongiDTO createGongi(GongiDTO gongiDTO) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into gongi(num,writer,title,content) ");
		sql.append(" values(('공지사항.'||gongi_num_seq.nextval),?,?,?) ");
		jt.update(sql.toString(),
							gongiDTO.getWriter(),
							gongiDTO.getTitle(),
							gongiDTO.getContent());
		return findGongi(gongiDTO.getNum());
	}

	/**
	 * 공지 조회
	 */
	@Override
	public GongiDTO findGongi(String num) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select num,writer,title,content,wdate,udate ");
		sql.append("from gongi ");
		sql.append("where num=? ");
		
		GongiDTO gongiDTO = jt.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(GongiDTO.class),num);
		return gongiDTO;
	}

	/**
	 * 공지 수정
	 */
	@Override
	public GongiDTO editGongi(String num, GongiDTO gongiDTO) {
		StringBuffer sql = new StringBuffer();
		sql.append("update gongi ");
		sql.append("set writer=?, ");
		sql.append("    title=?, ");
		sql.append("    content=?, ");
		sql.append("     udate=? ");
		sql.append("where num=? ");
		
		jt.update(sql.toString(),
				      gongiDTO.getWriter(),
				      gongiDTO.getTitle(),
				      gongiDTO.getContent(),
				      new java.util.Date(),
				      num);

		return findGongi(num);
	}

	/**
	 * 공지 삭제
	 */
	@Override
	public void deleteGongi(String num) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from gongi ");
		sql.append("where num = ? ");
		
		jt.update(sql.toString(), num);

	}

	/**
	 * 공지 목록
	 */
	@Override
	public List<GongiDTO> GongiList() {
		StringBuffer sql = new StringBuffer();
		sql.append("select num,writer,title,content,wdate,udate ");
		sql.append("from gongi ");
		
		List<GongiDTO> list = jt.query(sql.toString(), new BeanPropertyRowMapper<>(GongiDTO.class));
		return list;
	}

}
