package org.spring.my.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private String userid;
	private String passwd;
	private String name;
	private String nicname;
	private String email;
	private String tel;
	private String simplelogin;
	private Date regdate;
}
