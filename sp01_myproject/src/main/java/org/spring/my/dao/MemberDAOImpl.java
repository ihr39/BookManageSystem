package org.spring.my.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.Inquiry;
import org.spring.my.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void insert(Member member) {
		// TODO Auto-generated method stub
		sqlSession.insert("org.spring.my.MemberMapper.insert",member);
	}

	@Override
	public Member selectOne(String userid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("org.spring.my.MemberMapper.selectOne",userid);
	}

	@Override
	public int update(Member member) {
		// TODO Auto-generated method stub
		return sqlSession.update("org.spring.my.MemberMapper.update",member);
	}

	@Override
	public void delete(String userid) {
		// TODO Auto-generated method stub
		sqlSession.delete("org.spring.my.MemberMapper.delete",userid);
	}

	@Override
	public void passwdUpdate(Member member) {
		// TODO Auto-generated method stub
		sqlSession.update("org.spring.my.MemberMapper.passwdUpdate",member);
	}

	@Override
	public Member nicCheck(String nicname) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("org.spring.my.MemberMapper.nicnameCheck", nicname);
	}

	@Override
	public void naverJoin(Map<String, String> resultmap) {
		// TODO Auto-generated method stub
		sqlSession.insert("org.spring.my.MemberMapper.naverJoin",resultmap);
	}

	@Override
	public void kakaoJoin(Map<String, String> resultmap) {
		// TODO Auto-generated method stub
		sqlSession.insert("org.spring.my.MemberMapper.kakaoJoin",resultmap);
	}

	@Override
	public void inq_insert(Inquiry inquiry) {
		// TODO Auto-generated method stub
		sqlSession.insert("org.spring.my.MemberMapper.inq_insert",inquiry);
	}


}
