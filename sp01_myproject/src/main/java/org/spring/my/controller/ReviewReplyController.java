package org.spring.my.controller;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.ReviewReply;
import org.spring.my.service.ReviewReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("reply")
public class ReviewReplyController {
	@Autowired
	ReviewReplyService reviewReplyService;
	//리뷰의 댓글들 처리
	@ResponseBody
	@PostMapping("insert")
	public List<Map<String,Object>> insert(@RequestBody ReviewReply reviewReply) {
		reviewReplyService.insert(reviewReply);
		List<Map<String,Object>> rlist=reviewReplyService.selectlist(reviewReply);
		return rlist;
	}
	//리뷰 리스트 보이게 하기
	@ResponseBody
	@GetMapping("list")
	public List<Map<String,Object>> seletList(@RequestBody ReviewReply reviewReply) {
		List<Map<String,Object>> rlist=reviewReplyService.selectlist(reviewReply);
		return rlist;
	}
	
	@ResponseBody
	@GetMapping(value="delete",produces = "application/json; charset=utf-8")
	public String delete(ReviewReply reviewReply) {
		reviewReplyService.delete(reviewReply);
		return "삭제완료";
	}
	
	@ResponseBody
	@PostMapping(value="update",produces = "application/json; charset=utf-8")
	public String update(@RequestBody ReviewReply reviewReply) {
		reviewReplyService.update(reviewReply);
		return "수정완료";
	}
	
	@ResponseBody
	@GetMapping(value="selectList/{reviewno}")
	public List<ReviewReply> selectList(@PathVariable("reviewno") int reviewno) {
		List<ReviewReply> replylist=reviewReplyService.replySelectList(reviewno);
		return replylist;
	}
}
