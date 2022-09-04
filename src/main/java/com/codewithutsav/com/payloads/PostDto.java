package com.codewithutsav.com.payloads;

import java.util.Date;



import com.codewithutsav.com.entities.Category;
import com.codewithutsav.com.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	
	private String title;
	
	private String content;
	
    private String imageName;
	
	private Date addedDate;
	
	
	private CategoryDto category;
	
	
	private UserDto user;
	
}
