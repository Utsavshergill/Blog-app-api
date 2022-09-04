package com.codewithutsav.com.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithutsav.com.entities.Category;
import com.codewithutsav.com.entities.Post;
import com.codewithutsav.com.entities.User;
import com.codewithutsav.com.exceptions.ResourceNotFoundException;

import com.codewithutsav.com.payloads.PostDto;

import com.codewithutsav.com.repositories.CategoryRepo;
import com.codewithutsav.com.repositories.PostRepo;
import com.codewithutsav.com.repositories.UserRepo;
import com.codewithutsav.com.services.PostService;


@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public PostDto createPost(PostDto postDto ,Integer userId ,Integer categoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category id",categoryId));
		
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public Post updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}
    @Override
	public List<Post> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
