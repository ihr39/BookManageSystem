package org.spring.my.service;

import java.util.List;
import java.util.Map;

import org.spring.my.dao.BookDAO;
import org.spring.my.dao.ReviewDAO;
import org.spring.my.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private ReviewDAO reviewDAO;
	@Autowired
	private BookDAO bookDAO;
	
	@Transactional
	@Override
	public void insert(Review review) {
		// TODO Auto-generated method stub
		reviewDAO.insert(review);
		bookDAO.gradeUpdate(review.getIsbn());
	}
	@Override
	public List<Map<String,Object>> selectList(String isbn) {
		// TODO Auto-generated method stub
		return reviewDAO.selectList(isbn);
	}
	@Override
	public void delete(String reviewno) {
		// TODO Auto-generated method stub
		reviewDAO.delete(reviewno);
	}
	@Override
	public void update(Review review,String nicname) {
		// TODO Auto-generated method stub
		review.setNicname(nicname);
		reviewDAO.update(review);
	}

}
