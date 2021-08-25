package org.spring.my.dao;

import java.util.Map;

import org.spring.my.dto.Inquiry;
import org.spring.my.dto.Member;

public interface MemberDAO {
	//회원가입
	public void insert(Member member);
	public Member selectOne(String userid);
	public int update(Member member);
	public void delete(String userid);
	public void passwdUpdate(Member member);
	public Member nicCheck(String nicname);
	public void naverJoin(Map<String,String> resultmap);
	public void kakaoJoin(Map<String,String> resultmap);
	public void inq_insert(Inquiry inquiry);
}
