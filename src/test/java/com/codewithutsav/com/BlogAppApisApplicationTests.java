package com.codewithutsav.com;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codewithutsav.com.repositories.UserRepo;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	private UserRepo userRepo;	
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void repoTest()

	{
		String className=this.userRepo.getClass().getName();
		String packName=this.userRepo.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packName);
		
	}

}
