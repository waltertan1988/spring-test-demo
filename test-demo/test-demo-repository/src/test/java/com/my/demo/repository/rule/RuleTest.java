package com.my.demo.repository.rule;

import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import com.my.demo.domain.User;
import com.my.demo.repository.UserRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(BlockJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/META-INF/spring/applicationContext-*-app.xml" })
public class RuleTest {
	
	@ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
	
	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void test1FindByUserRealName(){
		List<User> userList = userRepository.findByUserRealName("张三");
		Assert.assertTrue(userList.size()>0);
	}
}
