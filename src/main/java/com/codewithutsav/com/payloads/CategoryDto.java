package com.codewithutsav.com.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotEmpty
	@Size(min=3,message="min size for category title is 3")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min=15,message="min size of category description is 3")
	private String categoryDescription;

}
