package com.codewithutsav.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithutsav.com.payloads.UserDto;
 //import com.codewithutsav.com.services.UserService;
import com.codewithutsav.com.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
	{
		UserDto createUserDto =this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto ,HttpStatus.CREATED);
		
		
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userdto, @PathVariable("UserId") Integer uid)
	{
		UserDto updatedUser=this.userService.updateUser(userdto,uid);
		return ResponseEntity.ok(updatedUser);
	}
	khjkhk
	 
	
	

}
