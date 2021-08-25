package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.Book;
import org.spring.my.dto.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOImpl implements BookDAO{
	@Autowired
	SqlSession sqlSession;
	@Override
	public void insert(Map<String,String> resultmap) {
		// TODO Auto-generated method stub
		sqlSession.insert("org.spring.my.BookMapper.insert",resultmap);
	}
	@Override
	public Map<String,Object> selectOne(Map<String, String> keymap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("org.spring.my.BookMapper.selectOne",keymap);
	}
	@Override
	public List<Map<String,Object>> selectList(Page page) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("org.spring.my.BookMapper.selectList",page);
	}
	@Override
	public List<Map<String,Object>> bookLikeList(String userid) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("org.spring.my.BookMapper.bookLikeList", userid);
	}
	@Override
	public List<Map<String, Object>> bookReviewList(String nicname) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("org.spring.my.BookMapper.bookReviewList",nicname);
	}
	@Override
	public void gradeUpdate(String isbn) {
		// TODO Auto-generated method stub
		sqlSession.update("org.spring.my.BookMapper.gradeUpdate",isbn);
	}
	@Override
	public List<Map<String,Object>> selectListGrade() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("org.spring.my.BookMapper.selectListGrade");
	}
	@Override
	public List<Map<String, Object>> selectListLikeCnt() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("org.spring.my.BookMapper.selectListLikeCnt");
	}
	@Override
	public int bookListCnt(Page page) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("org.spring.my.BookMapper.bookListCnt",page);
	}


}
