package org.spring.my.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.my.dao.BookDAO;
import org.spring.my.dto.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	BookAPI bookAPI;
	@Autowired
	BookDAO bookDAO;

	@Override
	public List<Map<String,Object>> selectList(Page page) throws Exception {
		//페이징 처리
		if(page.getCurPage()==0) page.setCurPage(1);
		int totalCnt=bookDAO.bookListCnt(page);
		int totalPage=totalCnt/page.getPerPage();
		if(totalCnt%page.getPerPage()!=0) totalPage+=1;
		page.setTotalPage(totalPage);
		
		int startNo=(page.getCurPage()-1)*page.getPerPage()+1; //현재 페이지의 시작하는 숫자 2페이지면 11이 시작
		int endNo=(startNo+page.getPerPage()) - 1; //현재 페이지의 마지막 숫자 2페이지면 21번이 끝
		
		int startPage=page.getCurPage()-((page.getCurPage()-1)%page.getPerBlock()); //시작하는 페이지
		int endPage=(startPage+page.getPerBlock())-1; //마지막 페이지의 넘버
		
		if(endPage>totalPage) endPage=totalPage;
		
		page.setEndNo(endNo);
		page.setEndPage(endPage);
		page.setStartNo(startNo);
		page.setStartPage(startPage);
		
		List<Map<String,Object>> book=bookDAO.selectList(page);
		
		if(book.size()==0) {
			bookAPI.NaverBook(page.getQuery());
		}
		return bookDAO.selectList(page);
	}
	
	@Override
	public Map<String,Object> selectOne(String isbn,String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> keymap=new HashMap<>();
		keymap.put("isbn", isbn);
		keymap.put("userid", userid);
		return bookDAO.selectOne(keymap);
	}

	@Override
	public void insert(Map<String, String> resultmap) {
		// TODO Auto-generated method stub
		bookDAO.insert(resultmap);
	}

	@Override
	public List<Map<String, Object>> bookLikeList(String userid) {
		// TODO Auto-generated method stub
		return bookDAO.bookLikeList(userid);
	}

	@Override
	public List<Map<String, Object>> bookReviewList(String nicname) {
		// TODO Auto-generated method stub
		return bookDAO.bookReviewList(nicname);
	}

	@Override
	public List<Map<String,Object>> selectListGrade() {
		// TODO Auto-generated method stub
		return bookDAO.selectListGrade();
	}

	@Override
	public List<Map<String, Object>> selectListLikeCnt() {
		// TODO Auto-generated method stub
		return bookDAO.selectListLikeCnt();
	}
	
}
