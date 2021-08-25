package org.spring.my.service;

import java.util.Map;

public interface NaverLoginService {
	//네이버 인증 api url 형성
	public Map<String,String> getApiUrl() throws Exception;
	//네이버 유저 프로필에 접근하기 위한 토큰 생성
	public String getToken(String state,String code) throws Exception;
	//토큰을 받아서 네이버 유저 정보에 접근
	public Map<String,String> getNaverUserInfo(String access_token) throws Exception;
//	public void insert(Map<String,String> resultmap);
}
