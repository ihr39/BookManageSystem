package org.spring.my.dto;

import lombok.Data;

@Data
public class Page {
	private int totalCnt; //총 게시물의 수
	private int totalPage; //총 페이지 수
	private int perPage=10; //한 페이지당 게시물의 수
	private int perBlock=10; //한 블럭(1,2,3,4,5) 당의 게시물수
	private int curPage; //현재 몇 페이지인지
	private int startNo; //그 페이지의 시작하는 첫 숫자
	private int endNo; //그 페이지의 끝나는 숫자
	private int startPage; //시작하는 페이지 넘버
	private int endPage; // 끝나는 페이지 넘버
	private String findkey;
	private String query;
}
