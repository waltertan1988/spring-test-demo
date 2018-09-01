package com.my.demo.repository.springtest.transaction.basic;

import java.util.Date;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.ErrorMode;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.my.demo.domain.User;
import com.my.demo.repository.UserRepository;
import com.my.demo.repository.springtest.extend.AbstractDemoRepositoryTest;

public class DemoTransactionTest extends AbstractDemoRepositoryTest{
	
	@Autowired
	private UserRepository userRepository;

	private JdbcTemplate jdbcTemplate;
	@Autowired
	private void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate();
		this.jdbcTemplate.setDataSource(dataSource);
	}
	
	@BeforeTransaction
	private void beforeTransaction(){
		System.out.println(">>>>>> beforeTransaction()");
	}
	
	@AfterTransaction
	private void afterTransaction(){
		System.out.println(">>>>>> afterTransaction()");
	}
	
	private void saveUser(String username, String password){
		User user = new User();
		user.setUsername(username);
		user.setUserRealName(username);
		user.setPassword(password);
		user.setGender("M");
		user.setBirthday(new Date());
		userRepository.save(user);
	}
	
	@Test
	@Transactional
//	@Commit
//	@Rollback(false) //等价于@Commit
	public void test1Save(){
		
		String username = "SCOTT";
		String password = "TIGER";
		String tableName = this.getTableNameFromDomain(User.class);
		
		int before = JdbcTestUtils.countRowsInTable(jdbcTemplate, tableName);
		this.saveUser(username, password);
		int after = JdbcTestUtils.countRowsInTable(jdbcTemplate, tableName);
		Assert.assertTrue(after==before+1);
		
		String testCondition = String.format("username='%s' AND password='%s'", username, password);
		int insertCount = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, tableName, testCondition);
		
		Assert.assertTrue(1==insertCount);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
//	@Commit
	public void test2GetUserWithJdbcTestUtilsScript(){
		JdbcTestUtils.executeSqlScript(jdbcTemplate, new ClassPathResource("/SQL/t_user.init.sql"), false);
		
		User user = userRepository.findByUsername("Reiko");
		Assert.assertNotNull(user);
		Assert.assertTrue("12345678".equals(user.getPassword()));
	}
	
	@Test
	@Transactional
	@SqlGroup({
	    @Sql(scripts = "/SQL/t_user.init.sql",
	    	executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, 
	    	config = @SqlConfig(errorMode=ErrorMode.FAIL_ON_ERROR)),
	    @Sql(scripts = "/SQL/t_user.upd.sql",
	    	executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, 
	    	config = @SqlConfig(commentPrefix = "--", errorMode=ErrorMode.FAIL_ON_ERROR))
	})
	public void test3GetUserWithSqlAnnotationScript(){
		User user = userRepository.findByUsername("Reiko");
		Assert.assertNotNull(user);
		Assert.assertTrue("123456789".equals(user.getPassword()));
	}
	
	@Test
	@Repeat(2)// 无法用于测试幂等性
	@Transactional
	public void test4Repeat() throws InterruptedException{
		String username = "SCOTT";
		String password = "TIGER";
		this.saveUser(username, password);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	@Transactional
	public void test5RepeatWithoutAnnotation(){
		// 测试幂等性要用以下方式
		String username = "SCOTT";
		String password = "TIGER";
		for(int i=0;i<2;i++){
			this.saveUser(username, password);
		}
	}
	
	@Test
	@Timed(millis=500L)
	@Transactional
	public void test6TimedVsJUnitTimeout() throws InterruptedException{
		Thread.sleep(1000);//超时后仍然会执行后面的代码
		System.out.println(">>>>>>>Finish");
	}
	
	@Test(timeout=500)
	@Transactional
	public void test7TimedVsJUnitTimeout() throws InterruptedException{
		Thread.sleep(1000);//超时后不会执行后面的代码
		System.out.println(">>>>>>>Finish");
	}
}
