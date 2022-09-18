package com.codewithutsav.com.controllers;

import com.codewithutsav.com.config.AppConstants;
import com.codewithutsav.com.payloads.ApiResponse;
import com.codewithutsav.com.payloads.PostDto;
import com.codewithutsav.com.services.FileService;
import com.codewithutsav.com.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;
	
	//create
	
	@PostMapping("users/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			)
	{
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	
	}
	
	//get by user
	
	@GetMapping("users/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
	
	{
		List<PostDto> posts=this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//get by category
	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
	
	{
		List<PostDto> posts=this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//get all posts
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost(@RequestParam(value="pageNumber" ,defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
													@RequestParam(value = "pageSize",defaultValue =AppConstants.PAGE_SIZE,required = false) Integer pageSize,
													@RequestParam(value="sortBy",defaultValue =AppConstants.SORT_BY,required = false)String sortBy,
													@RequestParam(value="sortDir",defaultValue =AppConstants.SORT_DIR,required = false)String sortDir)
	{
		List<PostDto> allPost =this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
	}
	
	
	//get post detail by id
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		PostDto postDto=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	//delete the post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return new ApiResponse("post Is successfully deleted..!!",true);
	}
	//update the post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable Integer postId)
	{
		PostDto updatePost=this.postService.updatePost(postDto,postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);


	}

	//search
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(
		@PathVariable("keyword") String keywords)
	{
   List<PostDto> results=this.postService.searchPosts(keywords);
   return new ResponseEntity<List<PostDto>>(results,HttpStatus.OK);
	}

	//post Image Upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image")MultipartFile image ,
			@PathVariable Integer postId
			) throws IOException
	{
		PostDto postDto=this.postService.getPostById(postId);

		String fileName=this.fileService.uploadImage(path,image);
		postDto.setImageName(fileName);
		PostDto updatePost=this.postService.updatePost(postDto,postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);

	}
	//method to serve file
	@GetMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException
	{
		InputStream resource=this.fileService.getResource(path,imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());


	}



}
