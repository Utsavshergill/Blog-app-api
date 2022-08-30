package com.codewithutsav.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithutsav.com.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
