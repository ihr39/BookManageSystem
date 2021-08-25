package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.ReviewReply;

public interface ReviewReplyDAO {
	public void insert(ReviewReply reviewReply);
	public void reviewStepUpdate(ReviewReply reviewReply); //댓글 등록시 step +1
	public List<Map<String,Object>> selectlist(ReviewReply reviewReply);
	public void delete(ReviewReply reviewReply);
	public void ReviewReplyStepUpdate(ReviewReply reviewReply); //댓글 삭제시 step -1
	public void update(ReviewReply reviewReply);//댓글 수정시
	public List<ReviewReply> replySelectList(int reviewno);//댓글 리스트
}
