package com.my.demo.service.test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.my.demo.service.MockDemoService;
import com.my.demo.service.UnimplementedSubService1;
import com.my.demo.service.UnimplementedSubService2;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {
		"classpath*:/META-INF/spring/applicationContext-*-app.xml",
		"classpath*:/META-INF/spring/applicationContext-*-test.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MockitoDiTest {

	@Autowired
	private MockDemoService demoService;

	@Autowired
	private UnimplementedSubService1 unimplementedSubService1;// @Autowired注入mock
	@Autowired
	private UnimplementedSubService2 unimplementedSubService2;// @Autowired注入mock

	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void test1MockAutowired() {
		// 定义Mock的输入值和返回值
		String sth = "Hello";
		when(unimplementedSubService1.appendComma(sth)).thenReturn(sth + ", ");
		when(unimplementedSubService2.getWhatAppendFullStop(anyString())).thenReturn("Test.");

		// 结果验证
		String actual = demoService.saySthToSpringWhat(sth, "Test");
		String expected = "Hello, Spring Test.";
		Assert.assertTrue(expected.equals(actual));

		// 行为验证
		verify(unimplementedSubService1, times(1)).appendComma(anyString());
		verify(unimplementedSubService2, times(1)).getWhatAppendFullStop(anyString());
	}
	
	@Test
	public void test2MockAutowired() {
		String actual = demoService.saySthToSpringWhat("Hello", "MVC");
		Assert.assertTrue(!"Hello, Spring Test.".equals(actual));
	}
}
