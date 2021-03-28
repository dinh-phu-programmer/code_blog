package com.dinhphu.blog;

import com.dinhphu.blog.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private UserService service;

	@Test
	void contextLoads() {
	}

	

}
