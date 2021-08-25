package org.spring.my.service;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.Review;
import org.spring.my.dto.ReviewReply;

public interface ReviewReplyService {
	public void insert(ReviewReply reviewReply);
	public List<Map<String,Object>> selectlist(ReviewReply reviewReply);
	public void delete(ReviewReply reviewReply);
	public void update(ReviewReply reviewReply);
	public List<ReviewReply> replySelectList(int reviewno);
}
