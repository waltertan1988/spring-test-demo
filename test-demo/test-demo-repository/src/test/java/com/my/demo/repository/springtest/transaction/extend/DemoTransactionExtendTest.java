package com.my.demo.repository.springtest.transaction.extend;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.my.demo.domain.User;
import com.my.demo.repository.UserRepository;

public class DemoTransactionExtendTest extends AbstractServiceTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
//	@Commit
	public void test1SaveUser() throws ParseException{
		String tableName = super.getTableNameFromDomain(User.class);
		
		int before = super.countRowsInTable(tableName);
		User user = new User();
		user.setUsername("SCOTT");
		user.setUserRealName("SCOTT");
		user.setPassword("TIGER");
		user.setGender("M");
		user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("2048-10-24"));
		userRepository.save(user);
		
		int after = super.countRowsInTable(tableName);
		Assert.assertTrue(after==before+1);
		
		int insertCount = super.countRowsInTableWhere(tableName, "username='SCOTT' AND password='TIGER'");
		Assert.assertTrue(1==insertCount);
	}
	
	@Test
	public void test2GetUser(){
		super.executeSqlScript("classpath:/SQL/t_user.init.sql", false);
		
		User user = userRepository.findByUsername("Reiko");
		Assert.assertNotNull(user);
		Assert.assertTrue("12345678".equals(user.getPassword()));
	}
}
