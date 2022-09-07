package com.codewithutsav.com.services;

import com.codewithutsav.com.entities.Post;
import com.codewithutsav.com.payloads.PostDto;

import java.util.List;


public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto ,Integer userId ,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto ,Integer postId);
	
	
	//delete
	void deletePost(Integer postId);
	
	
	//all user post
	List<PostDto> getAllPost();
	
	
	//single user
	
	PostDto getPostById(Integer postId);
	
	
	//get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	
	//get all post by user
	List<PostDto> getPostByUser(Integer userId);
	
	//search posts
	List<Post> searchPosts(String keyword);

}
