package org.spring.my.service;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.Review;

public interface ReviewService {
	public void insert(Review review);
	public List<Map<String,Object>> selectList(String isbn);
	public void delete(String reviewno);
	public void update(Review review,String nicname);
}
