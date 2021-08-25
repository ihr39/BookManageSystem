package org.spring.my.service;

import java.util.Map;

import org.spring.my.dto.MemberManage;

public interface MemberManageService {
	public void bookLike(String isbn,String userid);
	public void bookLikeDelete(String isbn,String userid);
	public void reviewLike(String reviewno,String userid);
	public void reviewLikeDelete(String reviewno,String userid);
}
