package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.Book;
import org.spring.my.dto.Page;

public interface BookDAO {
	public void insert(Map<String,String> resultmap);
	public Map<String,Object> selectOne(Map<String, String> keymap);
	public List<Map<String,Object>> selectList(Page page);
	public List<Map<String,Object>> bookLikeList(String userid);
	public List<Map<String,Object>> bookReviewList(String nicname);
	public void gradeUpdate(String isbn);
	public List<Map<String,Object>> selectListGrade();// 별점 순으로 정렬
	public List<Map<String,Object>> selectListLikeCnt(); //책 좋아요 순
	public int bookListCnt(Page page);
}
