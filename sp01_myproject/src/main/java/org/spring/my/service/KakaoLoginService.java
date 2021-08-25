package org.spring.my.service;

import java.util.Map;

public interface KakaoLoginService {
	//인가코드 받을 메소드
	public Map<String, String> KakaoURL() throws Exception;
	public String kakaoTokenGet(String code, String state) throws Exception;
	public Map<String,String> kakaoUserInfo(String access_token) throws Exception;
}	
