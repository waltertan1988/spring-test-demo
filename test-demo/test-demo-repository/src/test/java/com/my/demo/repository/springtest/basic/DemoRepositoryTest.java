package com.my.demo.repository.springtest.basic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.demo.domain.User;
import com.my.demo.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/META-INF/spring/applicationContext-*-app.xml" })
public class DemoRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void findByUsernameTest(){
		User user = userRepository.findByUsername("walter");
		Assert.assertNotNull(user);
	}
}
