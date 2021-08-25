package org.spring.my.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Inquiry {
	private int inq_num;
	private String inq_content;
	private String inq_subject;
	private String inq_file;
	private String userid;
	private MultipartFile file;
}
