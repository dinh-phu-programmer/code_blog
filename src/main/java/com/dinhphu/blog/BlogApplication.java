package com.dinhphu.blog;

import com.dinhphu.blog.dao.UserDao;
import com.dinhphu.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplication {

	private UserService userService;

	@Autowired
	public BlogApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserDao userDao){
		return args->{
//			for (int i =1 ;i<6;i++){
//				User user = new User("dinh"+i,"phu"+i,"dinhphu"+i,"123",i+"dinhphu@gmail.com","",null,null,null,null,true,false);
//				userDao.save(user);
//			}


//			Long start=System.currentTimeMillis();
//			this.userService.findById(350000L);
//			Long end = System.currentTimeMillis();
//			System.out.println("service time "+ (end-start));
//
//
//			Long startJpa=System.currentTimeMillis();
//			userDao.findById(350000L);
//
//			Long endJpa = System.currentTimeMillis();
//			System.out.println("jpa time "+ (endJpa-startJpa));

//			System.out.println(this.userService.findByUsername("dinhphu493"));

//			User newUser = new User("useridNew","dinhNew","phuNew","dinhphuNew","123New","Newdinhphu@gmail.com","new",null,null,"ADMIN",null,true,false);
//		User deleteUser= this.userService.findById(6L);
//			this.userService.delete(deleteUser);
		};
	}
}
