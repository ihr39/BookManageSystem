package org.spring.my.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.spring.my.dao.MemberDAO;
import org.spring.my.dto.Inquiry;
import org.spring.my.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private String uploadDir;
	
	@Override
	public void insert(Member member) {
		// TODO Auto-generated method stub
		String passwd=bCryptPasswordEncoder.encode(member.getPasswd());
		member.setPasswd(passwd);
		memberDAO.insert(member);
	}
	//아이디 체크
	@Override
	public Map<String,Object> idcheck(String userid) {
		// 0이면 사용가능 -1이면 사용 불가능
		Map<String,Object> resultmap=new HashMap<>();
		String msg;
		int recode;
		if(memberDAO.selectOne(userid)==null) {
			msg="사용가능한 아이디입니다";
			recode=0;
			resultmap.put("msg", msg);
			resultmap.put("recode", recode);
		}else {
			msg="사용 불가능한 아이디입니다";
			recode=-1;
			resultmap.put("msg", msg);
			resultmap.put("recode", recode);
		}
		return resultmap;
	}
	@Override
	public Map<String, Object> loginCheck(String userid,String passwd) {
		// 0은 로그인 성공은 바로 메인 1은 아이디 틀림 2는 비밀번호 틀림
		Map<String, Object> resultmap=new HashMap<>();
		Member member=memberDAO.selectOne(userid);
		String msg;
		int recode;
		String nicname="";
		if(member==null) {
			msg="존재하지 않는 회원입니다";
			recode=1;
		}else if(!bCryptPasswordEncoder.matches(passwd, member.getPasswd())) {
			msg="잘 못된 비밀번호입니다";
			recode=2;
		}else {
			msg="로그인 성공";
			recode=0;
			nicname=member.getNicname();
		}
		resultmap.put("msg", msg);
		resultmap.put("recode", recode);
		resultmap.put("nicname", nicname);
		return resultmap;
	}
	@Override
	public Member selectOne(String userid) {
		// TODO Auto-generated method stub
		return memberDAO.selectOne(userid);
	}
	@Override
	public String update(Member member) {
		// 회원정보 수정
		String msg="";
		if(0<memberDAO.update(member)) {
			msg="수정완료";
		}
		return msg;
	}

	@Override
	public void delete(String userid) {
		// TODO Auto-generated method stub
		memberDAO.delete(userid);
	}
	@Override
	public void passwdUpdate(String userid,String passwd) {
		// 비밀번호 변경
		Member member=new Member();
		member.setPasswd(bCryptPasswordEncoder.encode(passwd));
		member.setUserid(userid);
		memberDAO.passwdUpdate(member);
	}
	@Override
	public Map<String, Object> nicCheck(String nicname) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap=new HashMap<>();
		String msg;
		int recode;
		if(memberDAO.nicCheck(nicname)==null) {
			msg="사용가능한 닉네임입니다";
			recode=0;
			resultmap.put("msg", msg);
			resultmap.put("recode", recode);
		}else {
			msg="사용 불가능한 닉네입니다";
			recode=-1;
			resultmap.put("msg", msg);
			resultmap.put("recode", recode);
		}
		return resultmap;
	}
	
	@Transactional
	@Override
	public void inq_insert(Inquiry inquiry) {
		// TODO Auto-generated method stub
		String filename=FileUpload(inquiry.getFile());
		inquiry.setInq_file(filename);
		memberDAO.inq_insert(inquiry);
	}
	
	//파일 이름을 가져오기 위한 메소드
	public String FileUpload(MultipartFile file) {
		// 파일을 저장하고 이름을 string으로 저장
		String originfilename=file.getOriginalFilename();
		if(originfilename=="") return "";
		//파일 이름을 겹치지 않도록 파일 이름 생성
		String filename = System.currentTimeMillis()+originfilename; //현재 시간 + 실제 파일 이름
		File f =new File(uploadDir,filename); //파일을 저장할 위치
		try {
			file.transferTo(f); //파일 폴더에 저장
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filename;
	}

}
