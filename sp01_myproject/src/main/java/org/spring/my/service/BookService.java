package org.spring.my.service;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.Book;
import org.spring.my.dto.Page;

public interface BookService {
	public void insert(Map<String,String> resultmap);
	public List<Map<String,Object>> selectList(Page page) throws Exception;
	public Map<String,Object> selectOne(String isbn,String userid) throws Exception;
	public List<Map<String,Object>> bookLikeList(String userid);
	public List<Map<String,Object>> bookReviewList(String nicname);
	public List<Map<String,Object>> selectListGrade();// 별점 순으로 정렬
	public List<Map<String,Object>> selectListLikeCnt(); //책 좋아요 순
}
