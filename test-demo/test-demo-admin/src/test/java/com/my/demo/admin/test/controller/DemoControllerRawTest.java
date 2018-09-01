package com.my.demo.admin.test.controller;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.demo.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(locations = { "classpath*:/META-INF/spring/applicationContext-*-app.xml" }),
		@ContextConfiguration(locations = { "classpath*:/META-INF/spring/applicationContext-admin-mvc.xml" }) })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DemoControllerRawTest {
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	@Autowired
	private RequestMappingHandlerAdapter handleAdapter;

	@Test
	public void test1DemoList() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/admin/list");
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		HandlerExecutionChain chain = handlerMapping.getHandler(request);
		ModelAndView result = handleAdapter.handle(request, response, chain.getHandler());

		User user = (User) result.getModel().get("user");
		Assert.assertNotNull(user);
	}

	@Test
	public void test2GetUserData() throws Exception {
		final String USER_REAL_NAME = "张三";

		MockHttpServletRequest request = new MockHttpServletRequest("POST",
				"/admin/getUserData");
		MockHttpServletResponse response = new MockHttpServletResponse();

		request.addParameter("userRealName", USER_REAL_NAME);
		HandlerExecutionChain chain = handlerMapping.getHandler(request);
		handleAdapter.handle(request, response, chain.getHandler());

		List<User> users = json2Object(response.getContentAsString(), new TypeReference<List<User>>(){});
		Assert.assertTrue(users.size() > 0);

		for (User u : users) {
			Assert.assertTrue(USER_REAL_NAME.equals(u.getUserRealName()));
		}
	}

	@Test
	public void test3DemoRepositoryConverter() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest("GET",
				"/admin/repositoryConverter");
		MockHttpServletResponse response = new MockHttpServletResponse();

		request.addParameter("id", "1");
		HandlerExecutionChain chain = handlerMapping.getHandler(request);
		ModelAndView mv = handleAdapter.handle(request, response,
				chain.getHandler());

		User user = (User) mv.getModel().get("user");
		Assert.assertNotNull(user.getUsername());
	}
	
	private <T> T json2Object(String jsonStr, TypeReference<T> valueTypeRef)
			throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(jsonStr, valueTypeRef);
	}
}
