package org.spring.my;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookAPI{
	public void method() throws Exception {
		List<Map<String,String>> clist=new ArrayList<>();
		String ServiceKey="3F0F13A0FC5DCAE13E9EF03811F2F8206D1B30577F7811B687C8E7267D611BC0";
	       StringBuilder urlBuilder = new StringBuilder("http://book.interpark.com/api/search.api"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("keyey","UTF-8") + "=" + ServiceKey); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("output","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
	        urlBuilder.append("&" + URLEncoder.encode("start","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("maxResults","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*페이지 번호*/
	        //urlBuilder.append("&" + URLEncoder.encode("inputEncoding","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*한글 국가명*/
	        //urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]","UTF-8") + "=" + URLEncoder.encode("GH", "UTF-8")); /*ISO 2자리코드*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	}
	
	 public static void main(String[] args) throws Exception {
		 String ServiceKey="3F0F13A0FC5DCAE13E9EF03811F2F8206D1B30577F7811B687C8E7267D611BC0";
	       StringBuilder urlBuilder = new StringBuilder("http://book.interpark.com/api/search.api"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=" + ServiceKey); /*Service Key*/
	        urlBuilder.append("?" + URLEncoder.encode("query","UTF-8") + "=" + "해리포터");
	        urlBuilder.append("&" + URLEncoder.encode("start","UTF-8") + "=" +"1"); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("maxResults","UTF-8") + "=" +"10"); /*페이지 번호*/
	        urlBuilder.append("&" + URLEncoder.encode("output","UTF-8") + "=" + "JSON"); /*XML 또는 JSON*/ 
		       // urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]","UTF-8") + "=" + URLEncoder.encode(countryname, "UTF-8")); /*한글 국가명*/
		        //urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]","UTF-8") + "=" + URLEncoder.encode("GH", "UTF-8")); /*ISO 2자리코드*/
		        URL url = new URL(urlBuilder.toString());
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Content-type", "application/json");
		        System.out.println("Response code: " + conn.getResponseCode());
		        BufferedReader rd;
		        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		        }
		        StringBuilder sb = new StringBuilder();
		        String line;
		        while ((line = rd.readLine()) != null) {
		            sb.append(line);
		        }
		        rd.close();
		        conn.disconnect();
		        System.out.println(sb.toString());
		}
	}


