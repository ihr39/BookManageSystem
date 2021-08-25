package org.spring.my.service;

import java.util.HashMap;
import java.util.Map;

import org.spring.my.dao.MemberManageDAO;
import org.spring.my.dao.ReviewDAO;
import org.spring.my.dto.MemberManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberManageServiceImpl implements MemberManageService{
	@Autowired
	private MemberManageDAO memberManageDAO;
	@Autowired
	private ReviewDAO reviewDAO;
	
	@Override
	public void bookLike(String isbn, String userid) {
		// TODO Auto-generated method stub
		MemberManage memberManage=new MemberManage();
		memberManage.setNum(isbn);
		memberManage.setUserid(userid);
		memberManageDAO.bookLike(memberManage);
	}

	@Override
	public void bookLikeDelete(String isbn, String userid) {
		// TODO Auto-generated method stub
		MemberManage memberManage=new MemberManage();
		memberManage.setNum(isbn);
		memberManage.setUserid(userid);
		memberManageDAO.bookLikeDelete(memberManage);
	}
	
	@Override
	@Transactional
	public void reviewLike(String reviewno, String userid) {
		// TODO Auto-generated method stub
		MemberManage memberManage=new MemberManage();
		memberManage.setNum(reviewno);
		memberManage.setUserid(userid);
		memberManageDAO.reviewLikeInsert(memberManage);
		reviewDAO.updateReviewLikeCnt(reviewno);
	}

	@Override
	@Transactional
	public void reviewLikeDelete(String reviewno, String userid) {
		// TODO Auto-generated method stub
		MemberManage memberManage=new MemberManage();
		memberManage.setNum(reviewno);
		memberManage.setUserid(userid);
		memberManageDAO.reviewLikeDelete(memberManage);
		reviewDAO.minusReviewLikeCnt(reviewno);
	}

}
