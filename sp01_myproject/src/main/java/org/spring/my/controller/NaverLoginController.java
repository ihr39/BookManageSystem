package org.spring.my.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.spring.my.service.NaverLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("naverlogin")
public class NaverLoginController {
	//네이버 로그인 callback
	@Autowired
	NaverLoginService naverLoginService; 
	
	//네이버 api가 실행한 callback 주소
	@RequestMapping("callback")
	public String callback(String state,String code,HttpSession session) throws Exception{
		//정보를 얻기위한 토큰 획득
		String access_token=naverLoginService.getToken(state,code);
		//토큰을 이용해서 네이버 회원 접근
		Map<String, String> resultmap=naverLoginService.getNaverUserInfo(access_token);
		//회원가입
//		naverLoginService.insert(resultmap);
		session.setAttribute("userid", resultmap.get("name"));
		return "redirect:/book/index";
	}
}
