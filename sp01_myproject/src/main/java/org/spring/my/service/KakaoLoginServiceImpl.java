package org.spring.my.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.spring.my.dao.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KakaoLoginServiceImpl implements KakaoLoginService {
	@Autowired
	MemberDAO memberDAO;
	@Override
	public Map<String, String> KakaoURL() throws Exception {
		//카카오 인가코드를 받기 위한 메소드
		String client_id="236fbf2985560c2dce6453a554954a5c";
		String redirect_uri = URLEncoder.encode("http://localhost:8081/my/member/kakaocallback", "UTF-8");
		SecureRandom random = new SecureRandom();
	    //확인을 위해서 state는 랜덤으로 생성된 숫자로 구성
	    String state = new BigInteger(130, random).toString();
	    String apiURL = "https://kauth.kakao.com/oauth/authorize?client_id="+client_id+"&redirect_uri="+redirect_uri+"&response_type=code"; //code도 콜백으로 보내야함
	    apiURL += "&state=" + state; //인증을 하기 위한 랜덤코드 세션에 넣어야함
	    Map<String, String> resultmap=new HashMap<>();
	    resultmap.put("KakaoState", state);
	    resultmap.put("KakaoApiURL", apiURL);
	    return resultmap; //state와 카카오 로그인 url을 반환
	}
	
	//카카오 허용 토큰을 받기 위한 메소드
	@Override
	public String kakaoTokenGet(String code, String state) throws Exception {
		// 토큰 획득
		String client_id = "236fbf2985560c2dce6453a554954a5c";//애플리케이션 클라이언트 아이디값";
	    String redirect_uri = URLEncoder.encode("http://localhost:8081/my/member/kakaocallback", "UTF-8");
	    String apiURL;
	    apiURL = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + client_id;
	    apiURL += "&redirect_uri=" + redirect_uri;
	    apiURL += "&code=" + code; 
	    apiURL += "&state=" + state; 
	    
	    String access_token = "";
	    String refresh_token = "";
	    System.out.println("apiURL="+apiURL);
	    try {
		      URL url = new URL(apiURL);
		      //웹의 렌더링이 이루어지지 않고 순수한 웹 페이지만 읽는다
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode);
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      if(responseCode==200) {
		    	  System.out.println(res.toString());
		    	  //제이슨 파싱
		    	  JSONObject object=(JSONObject)new JSONParser().parse(res.toString());
		    	  access_token=(String)object.get("access_token");
		      }
		    } catch (Exception e) {
		      System.out.println(e);
		    }
		    return access_token;
	}
	
	//카카오 로그인 사용자 가져오기
	@Override
	public Map<String,String> kakaoUserInfo(String access_token) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> resultmap=new HashMap<>();
		String token = access_token; // 네이버 로그인 접근 토큰;
		String header = "Bearer " + token; // Bearer 다음에 공백 추가 토큰을 연결
		String apiURL = "https://kapi.kakao.com/v2/user/me";
	
		Map<String, String> requestHeaders = new HashMap<>();
	    requestHeaders.put("Authorization", header);
	    String responseBody = get(apiURL,requestHeaders);
	    
	    System.out.println(responseBody);
	    
	    JSONObject object=(JSONObject)new JSONParser().parse(responseBody);
	    object=(JSONObject)object.get("kakao_account");
	    JSONObject profile=(JSONObject)object.get("profile");
	    String nicname=(String)profile.get("nickname"); //이름이 닉네임
	    String userid=(String)object.get("email"); //카카오는 아이디가 이메일
	    
	    resultmap.put("nicname", nicname);
	    resultmap.put("userid", userid);
	    //카카오 회원가입
	    insert(resultmap);
	    
	    return resultmap;
	}
	
	private String get(String apiUrl, Map<String, String> requestHeaders){
	    HttpURLConnection con = connect(apiUrl);
	    try {
	        con.setRequestMethod("GET");
	        for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	            con.setRequestProperty(header.getKey(), header.getValue());
	        }
	        int responseCode = con.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	            return readBody(con.getInputStream());
	        } else { // 에러 발생
	            return readBody(con.getErrorStream());
	        }
	    } catch (IOException e) {
	        throw new RuntimeException("API 요청과 응답 실패", e);
	    } finally {
	        con.disconnect();
	    }
	}
	
	
	private HttpURLConnection connect(String apiUrl){
	    try {
	        URL url = new URL(apiUrl);
	        return (HttpURLConnection)url.openConnection();
	    } catch (MalformedURLException e) {
	        throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	    } catch (IOException e) {
	        throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	    }
	}
	
	
	private String readBody(InputStream body){
	    InputStreamReader streamReader = new InputStreamReader(body);
	    try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	        StringBuilder responseBody = new StringBuilder();

	        String line;
	        while ((line = lineReader.readLine()) != null) {
	            responseBody.append(line);
	        }
	        return responseBody.toString();
	    } catch (IOException e) {
	        throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	    }
	}
	
	public void insert(Map<String,String> resultmap) {
		//기존에 존재하는 회원일 경우 그냥 return 존재하지 않을 때만 가입시키기
		if(memberDAO.selectOne(resultmap.get("userid"))==null) {
			memberDAO.kakaoJoin(resultmap);
		}
	}

}
