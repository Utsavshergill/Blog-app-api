package com.codewithutsav.com.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithutsav.com.entities.Category;
import com.codewithutsav.com.entities.Post;
import com.codewithutsav.com.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>
{
	
	List<Post> findByUser(User user);
	List<Post> findByUser(Category category);
	

}
