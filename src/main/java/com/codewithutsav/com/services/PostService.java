package com.codewithutsav.com.services;

import java.util.List;

import com.codewithutsav.com.entities.Post;

import com.codewithutsav.com.payloads.PostDto;


public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto ,Integer userId ,Integer categoryId);
	
	//update
	Post updatePost(PostDto postDto ,Integer postId);
	
	
	//delete
	void deletePost(Integer postId);
	
	
	//all user post
	List<Post> getAllPost();
	
	
	//single user
	
	Post getPostById(Integer postId);
	
	
	//get all post by category
	List<Post> getPostByCategory(Integer categoryId);
	
	
	//get all post by user
	List<Post> getPostByUser(Integer userId);
	
	//search posts
	List<Post> searchPosts(String keyword);

}
