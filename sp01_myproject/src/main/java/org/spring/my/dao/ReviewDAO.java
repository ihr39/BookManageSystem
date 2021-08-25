package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.Review;

public interface ReviewDAO {
	public void insert(Review review);
	public void delete(String reviewno);
	public void update(Review review);
	public List<Map<String,Object>> selectList(String isbn);
	public void updateReviewLikeCnt(String reviewno);
	public void minusReviewLikeCnt(String reviewno);
}
