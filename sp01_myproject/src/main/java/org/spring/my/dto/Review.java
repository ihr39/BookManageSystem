package org.spring.my.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Review {
	private String shortreview;
	private String isbn;
	private String nicname;
	private String reviewscore;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
	private String regdate;
	private String reviewno;
	private int reviewstep;
	private int reviewlevel;
	private int reviewlikecnt;

}
