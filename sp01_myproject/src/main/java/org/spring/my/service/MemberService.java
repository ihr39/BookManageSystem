package org.spring.my.service;

import java.util.Map;

import org.spring.my.dto.Inquiry;
import org.spring.my.dto.Member;

public interface MemberService {
	public void insert(Member member);
	public Map<String,Object> idcheck(String userid);
	//public void nicCheck(String nicname);
	//로그인 확인
	public Map<String,Object> loginCheck(String userid,String passwd);
	public Member selectOne(String userid);
	public String update(Member member);
	public void delete(String userid);
	public void passwdUpdate(String userid,String passwd);
	public Map<String, Object> nicCheck(String nicname);
	public void inq_insert(Inquiry inquiry);
}
