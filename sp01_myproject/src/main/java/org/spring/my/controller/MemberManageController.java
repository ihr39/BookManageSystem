package org.spring.my.controller;

import javax.servlet.http.HttpSession;

import org.spring.my.dto.MemberManage;
import org.spring.my.service.MemberManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("manage")
public class MemberManageController {
	@Autowired
	MemberManageService memberManageService;
	//책 좋아요 취소
	@ResponseBody
	@GetMapping("booklikeCancle/{isbn}")
	public void booklike(@PathVariable("isbn") String isbn,HttpSession session) {
		String userid=(String)session.getAttribute("userid");
		memberManageService.bookLikeDelete(isbn, userid);
	}
	//책 좋아요
	@ResponseBody
	@GetMapping("booklike/{isbn}")
	public void booklikeCancle(@PathVariable("isbn") String isbn,HttpSession session) {
		String userid=(String)session.getAttribute("userid");
		memberManageService.bookLike(isbn, userid);
	}
	//리뷰 좋아요
	@ResponseBody
	@GetMapping("reviewlike/{reviewno}")
	public void reviewlike(@PathVariable("reviewno") String reviewno,HttpSession session) {
		String userid=(String)session.getAttribute("userid");
		memberManageService.reviewLike(reviewno, userid);
	}
	//리뷰 좋아요 취소
	@ResponseBody
	@GetMapping("reviewlikeCancle/{reviewno}")
	public void reviewlikeCancle(@PathVariable("reviewno") String reviewno,HttpSession session) {
		String userid=(String)session.getAttribute("userid");
		memberManageService.reviewLikeDelete(reviewno, userid);
	}
}
