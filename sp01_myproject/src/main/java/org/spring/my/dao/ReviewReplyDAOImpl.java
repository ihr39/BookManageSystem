package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.ReviewReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewReplyDAOImpl implements ReviewReplyDAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public void insert(ReviewReply reviewReply) {
		// TODO Auto-generated method stub
		sqlSession.insert("org.spring.my.ReviewReplyMapper.insert",reviewReply);
	}

	@Override
	public List<Map<String,Object>> selectlist(ReviewReply reviewReply) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("org.spring.my.ReviewReplyMapper.selectList",reviewReply);
	}

	@Override
	public void reviewStepUpdate(ReviewReply reviewReply) {
		// TODO Auto-generated method stub
		sqlSession.update("org.spring.my.ReviewReplyMapper.reviewStepUpdate",reviewReply);
	}

	@Override
	public void delete(ReviewReply reviewReply) {
		// TODO Auto-generated method stub
		sqlSession.delete("org.spring.my.ReviewReplyMapper.delete",reviewReply);
	}

	@Override
	public void ReviewReplyStepUpdate(ReviewReply reviewReply) {
		// TODO Auto-generated method stub
		sqlSession.update("org.spring.my.ReviewReplyMapper.ReviewReplyStepUpdate",reviewReply);
	}

	@Override
	public void update(ReviewReply reviewReply) {
		// TODO Auto-generated method stub
		sqlSession.update("org.spring.my.ReviewReplyMapper.update",reviewReply);
	}

	@Override
	public List<ReviewReply> replySelectList(int reviewno) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("org.spring.my.ReviewReplyMapper.replySelectList", reviewno);
	}


	
	

}
