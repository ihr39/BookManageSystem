package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDAOImpl implements ReviewDAO{
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void insert(Review review) {
		// TODO Auto-generated method stub
		sqlSession.insert("org.spring.my.ReviewMapper.insert",review);
	}

	@Override
	public List<Map<String,Object>> selectList(String isbn) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("org.spring.my.ReviewMapper.selectList",isbn);
	}

	@Override
	public void updateReviewLikeCnt(String reviewno) {
		// TODO Auto-generated method stub
		sqlSession.update("org.spring.my.ReviewMapper.updateReviewLikeCnt",reviewno);
	}

	@Override
	public void minusReviewLikeCnt(String reviewno) {
		// TODO Auto-generated method stub
		sqlSession.update("org.spring.my.ReviewMapper.minusReviewLikeCnt",reviewno);
	}

	@Override
	public void delete(String reviewno) {
		// TODO Auto-generated method stub
		sqlSession.delete("org.spring.my.ReviewMapper.delete",reviewno);
	}

	@Override
	public void update(Review review) {
		// TODO Auto-generated method stub
		sqlSession.update("org.spring.my.ReviewMapper.update",review);
	}
	

}
