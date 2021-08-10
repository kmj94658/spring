package com.kh.gongiapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.kh.gongiapp.dto.GongiDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jt.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql.toString(), new String[] {"num"});
				pstmt.setString(1, gongiDTO.getWriter());
				pstmt.setString(2, gongiDTO.getTitle());
				pstmt.setString(3, gongiDTO.getContent());
				return pstmt;
			}
		}, keyHolder);
		String key = keyHolder.getKeys().get("num").toString();
		log.info("키홀더:{}",key);
		return findGongi(keyHolder.getKeys().get("num").toString());		
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
		sql.append("order by num desc ");
		
		List<GongiDTO> list = jt.query(sql.toString(), new BeanPropertyRowMapper<>(GongiDTO.class));
		return list;
	}

}
