package com.codewithutsav.com.services.impl;

import com.codewithutsav.com.entities.Category;
import com.codewithutsav.com.entities.Post;
import com.codewithutsav.com.entities.User;
import com.codewithutsav.com.exceptions.ResourceNotFoundException;
import com.codewithutsav.com.payloads.PostDto;
import com.codewithutsav.com.repositories.CategoryRepo;
import com.codewithutsav.com.repositories.PostRepo;
import com.codewithutsav.com.repositories.UserRepo;
import com.codewithutsav.com.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post =this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost=this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);


	}

	@Override
	public void deletePost(Integer postId) {
	Post post =	this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
     this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
//
//		int pageSize=5;
//		int pageNumber=1;
       Sort sort=null;
		if (sortDir.equalsIgnoreCase("asc"))
		{
         sort=Sort.by(sortBy).ascending();

		}
		else
		{
			sort=Sort.by(sortBy).descending();
		}

		Pageable p= PageRequest.of(pageNumber,pageSize, sort);

		Page<Post> pagePost=this.postRepo.findAll(p);
		List<Post> allposts=pagePost.getContent();


		List<PostDto> postDtos=allposts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
		
		
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
		return this.modelMapper.map(post,PostDto.class);
		
	}
    @Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
    	Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		List<Post> posts=this.postRepo.findByCategory(cat);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
