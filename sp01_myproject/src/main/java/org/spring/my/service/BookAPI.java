package org.spring.my.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.spring.my.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookAPI{
 //isbn, 책 이름 저장
	@Autowired
	BookService bookService;
	
	public void NaverBook(String query) throws Exception {
	     String clientId = "hyIEPAz4My1f2Hkh2DYj"; //애플리케이션 클라이언트 아이디값"
	     String clientSecret = "mStkrqwK35"; //애플리케이션 클라이언트 시크릿값"


	     String text = null;
	     try {
	         text = URLEncoder.encode(query, "UTF-8");
	     } catch (UnsupportedEncodingException e) {
	         throw new RuntimeException("검색어 인코딩 실패",e);
	     }

	     String apiURL = "https://openapi.naver.com/v1/search/book?display=20&query=" + text;    // json 결과
	     //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
	     	
	     Map<String, String> requestHeaders = new HashMap<>();
	     requestHeaders.put("X-Naver-Client-Id", clientId);
	     requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	     String responseBody = get(apiURL,requestHeaders);
	     System.out.println("책"+responseBody); //출력결과값
	     JSONObject object = (JSONObject)new JSONParser().parse(responseBody);
	     JSONArray array=(JSONArray)object.get("items");
	     int result=0;
	     for(int i=0;i<array.size();i++) {
	    	 Map<String, String> resultmap=new HashMap<>();
	    	 object=(JSONObject)array.get(i);
	    	 String title=(String)object.get("title"); //책 제목
		     String link=(String)object.get("link"); //네이버 책 링크
		     String image=(String)object.get("image"); //표지
		     String author=(String)object.get("author"); //작가
		     String publisher=(String)object.get("publisher"); //출판사
		     String isbn=(String)object.get("isbn"); //isbn
		     isbn.replace("<b>", "");
		     isbn.replace("</b>", "");
		     String description=(String)object.get("description"); //출판사
		   	 resultmap.put("title", title);
		   	 resultmap.put("link", link);
		   	 resultmap.put("image", image);
		   	 resultmap.put("author", author);
		   	 resultmap.put("publisher", publisher);
		     resultmap.put("isbn", isbn);
		   	 resultmap.put("description", description);
		     bookService.insert(resultmap);
	    	 //blist.add(resultmap);
	     }   
	}

	private String get(String apiURL, Map<String, String> requestHeaders) {
		// TODO Auto-generated method stub
	     HttpURLConnection con = connect(apiURL);
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

	private HttpURLConnection connect(String apiURL) {
		// TODO Auto-generated method stub
		try {
	         URL url = new URL(apiURL);
	         return (HttpURLConnection)url.openConnection();
	     } catch (MalformedURLException e) {
	         throw new RuntimeException("API URL이 잘못되었습니다. : " + apiURL, e);
	     } catch (IOException e) {
	         throw new RuntimeException("연결이 실패했습니다. : " + apiURL, e);
	     }
	}

	private String readBody(InputStream body) {
		// TODO Auto-generated method stub
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
	
}

