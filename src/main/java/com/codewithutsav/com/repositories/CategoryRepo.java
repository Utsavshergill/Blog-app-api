package com.codewithutsav.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithutsav.com.entities.Category;

public interface CategoryRepo extends JpaRepository<Category ,Integer> {

}
