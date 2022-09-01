package com.codewithutsav.com.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithutsav.com.entities.User;
import com.codewithutsav.com.exceptions.ResourceNotFoundException;
import com.codewithutsav.com.payloads.UserDto;
import com.codewithutsav.com.repositories.UserRepo;
import com.codewithutsav.com.services.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		
		return this.userToDTo(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto,Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateUser = this.userRepo.save(user);
		UserDto userDto1=this.userToDTo(updateUser);
		
		
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		return this.userToDTo(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToDTo(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);
		
	

	}
	private User dtoToUser(UserDto userDto)
	{
		User user=this.modelMapper.map(userDto, User.class);
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;
		
	}
	public UserDto userToDTo(User user)
	{
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		
		return userDto;
		
	}

}
