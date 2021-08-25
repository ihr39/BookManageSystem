package org.spring.my.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.spring.my.dto.Page;
import org.spring.my.service.BookService;
import org.spring.my.service.MemberManageService;
import org.spring.my.service.MemberService;
import org.spring.my.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("book")
public class BookController {
	@Autowired
	BookService bookService;
	@Autowired
	MemberService memberService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	MemberManageService memberManageService;
	
	@GetMapping("main")
	public void bookmain(Page page,Model model) throws Exception {
		if(page.getQuery() == null) page.setQuery("");
		List<Map<String,Object>> blist=bookService.selectList(page);
		model.addAttribute("blist",blist);
		model.addAttribute("page",page);
	}
	
	@GetMapping("detail")
	public void detail(String isbn,Model model,HttpSession session) throws Exception {
		String userid=(String)session.getAttribute("userid");
		Map<String,Object> blist= bookService.selectOne(isbn,userid);
		model.addAttribute("blist",blist);
	}
	
	@GetMapping("index")
	public void index(String query,String findkey,Model model) throws Exception{
		List<Map<String,Object>> bookGradeList=null;
		List<Map<String,Object>> bookLikeCntList=null;
		if(findkey == null || findkey=="") {
			bookGradeList=bookService.selectListGrade();
			bookLikeCntList=bookService.selectListLikeCnt();
		}
		if(query == null) query="";
		model.addAttribute("bookGradeList",bookGradeList);
		model.addAttribute("bookLikeCntList",bookLikeCntList);
	}
	
}
