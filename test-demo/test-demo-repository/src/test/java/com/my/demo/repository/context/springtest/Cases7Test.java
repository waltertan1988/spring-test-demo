package com.my.demo.repository.context.springtest;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.demo.domain.User;
import com.my.demo.repository.UserRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/META-INF/spring/applicationContext-*-app.xml" })
public class Cases7Test {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	public void test1FindByUserRealName(){
		List<User> userList = userRepository.findByUserRealName("张三");
		Assert.assertTrue(userList.size()>0);
	}
}
