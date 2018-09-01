package com.my.demo.repository.junit.testcase;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.demo.domain.User;
import com.my.demo.repository.UserRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Cases2Test {
	
	private static ApplicationContext ac;
	private static UserRepository userRepository;

	@BeforeClass
	public static void setUp(){
		ac = new ClassPathXmlApplicationContext(new String[]{
			"classpath*:/META-INF/spring/applicationContext-*-app.xml"
		});
		
		userRepository = ac.getBean(UserRepository.class);
		
		System.out.println(String.format(">>>>>>ac2[hashCode=%s] startup at: %s", ac.hashCode() ,new Date(ac.getStartupDate())));
	}
	
	@Test
	public void test1FindByUserRealName(){
		List<User> userList = userRepository.findByUserRealName("张三");
		Assert.assertTrue(userList.size()>0);
	}
}