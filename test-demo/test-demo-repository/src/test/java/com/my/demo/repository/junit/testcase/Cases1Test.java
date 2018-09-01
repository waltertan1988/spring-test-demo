package com.my.demo.repository.junit.testcase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.demo.domain.User;
import com.my.demo.repository.UserRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Cases1Test {
	
	private static ApplicationContext ac;
	private static UserRepository userRepository;

	@BeforeClass
	public static void setUp(){
		ac = new ClassPathXmlApplicationContext(new String[]{
			"classpath*:/META-INF/spring/applicationContext-*-app.xml"
		});
		
		userRepository = ac.getBean(UserRepository.class);
		
		System.out.println(String.format(">>>>>>ac1[hashCode=%s] startup at: %s", ac.hashCode() ,new Date(ac.getStartupDate())));
	}
	
	@Before
	public void before() throws ParseException{
		User user = new User();
		user.setUsername("0009785");
		user.setPassword("000000");
		user.setGender("M");
		user.setUserRealName("张三");
		user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("2048-10-24"));
		userRepository.save(user);
	}
	
	@After
	public void after(){
		User user = userRepository.findByUsername("0009785");
		if(null != user){
			userRepository.delete(user);
		}
	}
	
	@Test
	public void test1FindByUserRealName(){
		List<User> userList = userRepository.findByUserRealName("张三");
		Assert.assertTrue(userList.size()>0);
	}
	
	@Test(timeout = 1000)
	public void test2Timeout() throws InterruptedException{
		Thread.sleep(2000);
		List<User> userList = userRepository.findByUserRealName("张三");
		Assert.assertTrue(userList.size()>0);
	}
	
	@Test(expected = ArithmeticException.class)
	public void test3ExpectedException() {
		int a = 1 / 0;
		System.out.println(a);
	}
	
	@Test
	@Ignore
	public void test4Ignore(){
		
	}
}
