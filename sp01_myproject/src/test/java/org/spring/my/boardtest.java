package org.spring.my;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
						"file:src/main/webapp/WEB-INF/spring/**/servlet-context.xml"})
public class boardtest {
	@Test
	public void test() {
//		//삽입
//		Board board =new Board();
//		board.setUserid("java");
//		board.setSubject("1");
//		board.setContent("1");
//		board.setIp("1");
//		System.out.println(board);
//		boardDAO.insert(board);
		//수정
//		Board board =new Board();
//		board.setBnum(1);
//		board.setUserid("java");
//		board.setSubject("test");
//		board.setContent("junut");
//		board.setIp("100.0.1.0");
//		boardDAO.update(board);
//		
//		Map<String,Object> findmap=new HashMap<>();
//		Usermanage usermanage=new Usermanage();
//		usermanage.setGubun("1");//1번이 게시글, 2번이 댓글
//		usermanage.setNum(1); //게시글 또는 댓글 번호
//		usermanage.setUserid("hong");
//		usermanage.setState("0");
//		
//		usermanageDAO.boardReadCount_insert_update(usermanage);
//		findmap.put("gubun", "1");//1번이 게시글, 2번이 댓글
//		findmap.put("num", bnum); //게시글 또는 댓글 번호
//		findmap.put("userid", userid);
//		findmap.put("state", "0");//0은 조회, 1은 좋아요, 2는 싫어요

//		if(usermanageDAO.selectOne(usermanage) == null){
//			usermanageDAO.insert(usermanage); //조회수 +1
//			boardDAO.updateReadCnt(bnum); //유저매니저 insert
//		}
	}

}
