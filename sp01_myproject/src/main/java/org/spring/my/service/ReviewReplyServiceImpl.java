package org.spring.my.service;

import java.util.List;
import java.util.Map;

import org.spring.my.dao.ReviewReplyDAO;
import org.spring.my.dto.ReviewReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewReplyServiceImpl implements ReviewReplyService{
	@Autowired
	 ReviewReplyDAO reviewReplyDAO;
	
	@Transactional
	@Override
	public void insert(ReviewReply reviewReply) {
		// TODO Auto-generated method stub
		int reviewstep=reviewReply.getReviewstep()+1;
		int reviewlevel=reviewReply.getReviewlevel()+1;
		reviewReply.setReviewstep(reviewstep);
		reviewReply.setReviewlevel(reviewlevel);
		reviewReplyDAO.reviewStepUpdate(reviewReply);
		reviewReplyDAO.insert(reviewReply);
	}

	@Override
	public List<Map<String,Object>> selectlist(ReviewReply reviewReply) {
		// TODO Auto-generated method stub
		return reviewReplyDAO.selectlist(reviewReply);
	}
	
	@Transactional
	@Override
	public void delete(ReviewReply reviewReply) {
		// TODO Auto-generated method stub
		reviewReplyDAO.delete(reviewReply);
		reviewReplyDAO.ReviewReplyStepUpdate(reviewReply);
	}

	@Override
	public void update(ReviewReply reviewReply) {
		// TODO Auto-generated method stub
		reviewReplyDAO.update(reviewReply);
	}

	@Override
	public List<ReviewReply> replySelectList(int reviewno) {
		// TODO Auto-generated method stub
		return reviewReplyDAO.replySelectList(reviewno);
	}

}
