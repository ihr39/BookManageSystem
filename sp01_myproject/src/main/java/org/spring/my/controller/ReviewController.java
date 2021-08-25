package org.spring.my.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.spring.my.dto.Review;
import org.spring.my.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("review")
@Controller
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
	@ResponseBody
	@PostMapping("insert")
	public String insert(@RequestBody Review review,Model model) {
		System.out.println("test"+review);
		reviewService.insert(review);
		
		return "성공";
	}
	
	@ResponseBody
	@GetMapping("list/{isbn}")
	public List<Map<String,Object>> list(@PathVariable("isbn") String isbn) {
		List<Map<String,Object>> rlist=reviewService.selectList(isbn);
		return rlist;
	}
	//리뷰 삭제
	@ResponseBody
	@GetMapping("delete/{reviewno}")
	public void delete(@PathVariable String reviewno) {
		reviewService.delete(reviewno);
	}
	//리뷰 수정
	@ResponseBody
	@PostMapping("update")
	public void update(@RequestBody Review review,HttpSession session) {
		String nicname=(String)session.getAttribute("nicname");
		reviewService.update(review,nicname);
	}
}
