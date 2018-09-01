package com.my.demo.repository.springtest.extend;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.my.demo.domain.User;
import com.my.demo.repository.UserRepository;

public class DemoRepository1Test extends AbstractDemoRepositoryTest {

	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void before(){
		System.out.println(String.format(">>>>>>ac1[hashCode=%s] startup at: %s", ac.hashCode() ,new Date(ac.getStartupDate())));
	}
	
	@Test
	public void findByUsernameTest(){
		User user = userRepository.findByUsername("walter");
		Assert.assertNotNull(user);
	}
}
