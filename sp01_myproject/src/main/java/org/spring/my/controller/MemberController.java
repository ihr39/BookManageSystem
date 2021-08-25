package org.spring.my.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.spring.my.dto.Inquiry;
import org.spring.my.dto.Member;
import org.spring.my.dto.Page;
import org.spring.my.service.BookService;
import org.spring.my.service.KakaoLoginService;
import org.spring.my.service.MemberService;
import org.spring.my.service.NaverLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberService memberService;
	@Autowired
	BookService bookService;
	@Autowired
	NaverLoginService naverLoginService;
	@Autowired
	KakaoLoginService kakaoLoginService;
	
	//회원가입 창으로 가는 메소드
	@GetMapping("join")
	public void join() {
		
	}
	//회원가입
	@PostMapping("join")
	public String join(Member member) {
		memberService.insert(member);
		return "redirect:login";
	}
	//아이디 중복체크
	@ResponseBody
	@RequestMapping("idcheck/{userid}")
	public Map<String,Object> idcheck(@PathVariable("userid") String userid) {
		Map<String,Object> resultmap=memberService.idcheck(userid);
		return resultmap;
	}
	
	//로그인 창으로 가는 메소드
	@GetMapping("login")
	public void login(Model model,HttpSession session) throws Exception{
		//url을 만듦 네아로 url과 state를 생성
		Map<String, String> naverResultmap=naverLoginService.getApiUrl();
		model.addAttribute("NaverApiURL",naverResultmap.get("NaverApiURL"));
		//네이버 로그인을 위한 state는 session으로 만든다
		session.setAttribute("NaverState",naverResultmap.get("NaverState"));
		//url을 만듦 카카오 로그인 url과 state를 생성
		Map<String, String> kakaoResultmap=kakaoLoginService.KakaoURL();
		model.addAttribute("KakaoApiURL",kakaoResultmap.get("KakaoApiURL"));
		//카카오 로그인을 위한 state는 session으로 만든다
		session.setAttribute("KakaoState", kakaoResultmap.get("KakaoState"));
		
	}
	
	//네이버 로그인을하고 생성된 토큰을 얻기위한 콜백주소
	@GetMapping("callback")
	public String callback(HttpSession session,String code) throws Exception {
		//네이버 로그인을 눌러서 로그인을 시도시 state을 가져와 인증을 한다
		String state=(String)session.getAttribute("NaverState");
		String access_token=naverLoginService.getToken(state, code);
		Map<String, String> resultmap=naverLoginService.getNaverUserInfo(access_token);
		session.setAttribute("userid", resultmap.get("userid"));
		session.setAttribute("nicname", resultmap.get("nicname"));
		return "redirect:/book/index";
		
	}
	
	//카카오 콜백 함수
	@GetMapping("kakaocallback")
	public String kakaocallback(HttpSession session,String code) throws Exception {
		String state=(String)session.getAttribute("KakaoState");
		String access_token=kakaoLoginService.kakaoTokenGet(code, state);
		Map<String, String> resultmap=kakaoLoginService.kakaoUserInfo(access_token);
		session.setAttribute("userid",resultmap.get("userid"));
		session.setAttribute("nicname",resultmap.get("nicname"));
		return "redirect:/book/index";
	}
	
	//로그인 시
	@PostMapping("login")
	public String login(String userid,String passwd,RedirectAttributes attr,HttpSession session) {
		Map<String, Object> resultmap=memberService.loginCheck(userid,passwd);
		if((int)resultmap.get("recode")!=0) {
			attr.addFlashAttribute("msg", resultmap.get("msg"));
			return "redirect:login";
		}
		session.setAttribute("userid", userid);
		session.setAttribute("nicname", resultmap.get("nicname"));
		return "redirect:/book/index";
	}

	//로그아웃
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/book/index";
	}
	
	//상세조회로
	@RequestMapping("detail")
	public void detail(HttpSession session,Model model) {
		String userid=(String)session.getAttribute("userid");
		String nicname=(String)session.getAttribute("nicname");
		List<Map<String, Object>> booklikelist=bookService.bookLikeList(userid);
		List<Map<String, Object>> bookreviewlist=bookService.bookReviewList(nicname);
		model.addAttribute("booklikelist",booklikelist);
		model.addAttribute("bookreviewlist",bookreviewlist);
	}
	
	//회원 정보 수정
	@RequestMapping("update")
	public String update(HttpSession session,Model model) {
		String userid=(String)session.getAttribute("userid");
		Member member=memberService.selectOne(userid);
		model.addAttribute("member",member);
		return "member/update";
	}
	
	//닉네임 체크
	@ResponseBody
	@GetMapping("nicCheck/{nicname}")
	public Map<String, Object> nicCheck(@PathVariable("nicname") String nicname
			,HttpSession session) {
		Map<String, Object> resultmap=memberService.nicCheck(nicname);
		return resultmap;
	}
	
	@PostMapping("update")
	public String update(Member member,Model model) {
		String msg=memberService.update(member);
		model.addAttribute("msg",msg);
		return "member/update";
	}
	
	//삭제
	@RequestMapping("delete")
	public String delete(HttpSession session,Model model) {
		String userid = (String)session.getAttribute("userid");
		memberService.delete(userid);
		model.addAttribute("msg","탈퇴완료");
		session.invalidate();
		return "book/index";
	}
	
	@GetMapping("passwdUpdate")
	public void passwdUpdate() {}
	
	@PostMapping("passwdUpdate")
	public String passwdUpdate(String passwd,HttpSession session,RedirectAttributes attr) {
		String userid = (String)session.getAttribute("userid");
		memberService.passwdUpdate(userid, passwd);
		attr.addFlashAttribute("msg", "비밀번호가 변경되었습니다 \\n 다시 로그인 해주세요");
		session.invalidate();
		return "redirect:login";
	}
	//문의 팝업창 띄우기
	@GetMapping("inquiry")
	public void inquiry() {}
	
	@PostMapping("inquiry")
	public void inquiry(Inquiry inquiry) {
		memberService.inq_insert(inquiry);
	}
	
	//지도
	@RequestMapping("contact")
	public String map() {
		return "member/contact";
	}
}
