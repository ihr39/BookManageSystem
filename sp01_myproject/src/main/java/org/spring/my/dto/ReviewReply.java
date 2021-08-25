package org.spring.my.dto;

import lombok.Data;

@Data
public class ReviewReply {
	private String content;
	private String nicname;
	private String regdate;
	private int reviewno;
	private String isbn;
	private int reviewstep;
	private int reviewlevel;
}
