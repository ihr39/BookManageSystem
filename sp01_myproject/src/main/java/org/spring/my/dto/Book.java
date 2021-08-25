package org.spring.my.dto;

import lombok.Data;

@Data
public class Book {
	private String isbn;
	private String title;
	private String author;
	private String image;
	private String description;
	private String publisher;
	private String link;
	private float grade;

}
