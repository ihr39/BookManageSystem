package org.spring.my.dao;

import java.util.Map;

import org.spring.my.dto.MemberManage;

public interface MemberManageDAO {
	public void bookLike(MemberManage memberManage);
	public void bookLikeDelete(MemberManage memberManage);
	public MemberManage selectOne(String userid);
	public void reviewLikeInsert(MemberManage memberManage);
	public void reviewLikeDelete(MemberManage memberManage);
}
