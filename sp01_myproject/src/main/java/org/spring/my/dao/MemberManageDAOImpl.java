package org.spring.my.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.MemberManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberManageDAOImpl implements MemberManageDAO{
	@Autowired
	SqlSession sqlSession;
	@Override
	public MemberManage selectOne(String userid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("org.spring.my.MemberManageMapper.selectOne",userid);
	}
	@Override
	public void bookLike(MemberManage memberManage) {
		sqlSession.update("org.spring.my.MemberManageMapper.bookLike",memberManage);
	}

	@Override
	public void bookLikeDelete(MemberManage memberManage) {
		// TODO Auto-generated method stub
		sqlSession.update("org.spring.my.MemberManageMapper.bookLikeDelete",memberManage);
	}
	@Override
	public void reviewLikeInsert(MemberManage memberManage) {
		// TODO Auto-generated method stub
		sqlSession.insert("org.spring.my.MemberManageMapper.reviewLikeInsert",memberManage);
	}
	@Override
	public void reviewLikeDelete(MemberManage memberManage) {
		// TODO Auto-generated method stub
		sqlSession.update("org.spring.my.MemberManageMapper.reviewLikeDelete", memberManage);
	}

}
