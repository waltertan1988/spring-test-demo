package com.my.demo.admin.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.my.demo.admin.test.AbstractControllerTest;
import com.my.demo.domain.User;

public class DemoControllerWebAppContextSetupTest extends AbstractControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void test1DemoList() throws Exception {
		mockMvc.perform(get("/admin/list")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("admin/list"))
				.andExpect(model().attributeExists("user"));
	}
	
	@Test
	public void test2Exception() throws Exception{
		mockMvc.perform(get("/admin/exception")).andExpect(status().isOk()).andExpect(view().name("error")).andExpect(model().attributeExists("exception"));
	}

	@Test
	public void test3GetUserData() throws Exception {
		
		final String USER_REAL_NAME = "张三";
		
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<String, String>();
		paramMap.put("userRealName", Arrays.asList(USER_REAL_NAME));
		MvcResult result = mockMvc
				.perform(post("/admin/getUserData").params(paramMap))
				.andExpect(status().isOk()).andReturn();

		List<User> users = json2Object(result.getResponse()
				.getContentAsString(), new TypeReference<List<User>>() {
		});
		Assert.assertTrue(users.size()>0);
		
		for (User u : users) {
			Assert.assertTrue(USER_REAL_NAME.equals(u.getUserRealName()));
		}
	}

	@Test
	public void test4GetUserDataRest() throws Exception {
		
		final String USER_REAL_NAME = "张三";
		
		MvcResult result = mockMvc
				.perform(post("/admin/getUserDataRest/{userRealName}", URLEncoder.encode(USER_REAL_NAME, "UTF-8")))
				.andExpect(status().isOk()).andReturn();

		List<User> users = json2Object(result.getResponse()
				.getContentAsString(), new TypeReference<List<User>>() {
		});
		Assert.assertTrue(users.size()>0);
		
		for (User u : users) {
			Assert.assertTrue(USER_REAL_NAME.equals(u.getUserRealName()));
		}
	}
	
	@Test
	public void test5GetUserDataByReq() throws Exception {
		
		final String USER_REAL_NAME = "张三";
		
		MvcResult result = mockMvc
				.perform(post("/admin/getUserDataByReq").param("userRealName", USER_REAL_NAME))
				.andExpect(status().isOk()).andReturn();

		List<User> users = json2Object(result.getResponse()
				.getContentAsString(), new TypeReference<List<User>>() {
		});
		Assert.assertTrue(users.size()>0);
		
		for (User u : users) {
			Assert.assertTrue(USER_REAL_NAME.equals(u.getUserRealName()));
		}
	}

	@Test
	public void test6Login() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isNotFound());
	}
	
	@Test
	public void test7DemoRepositoryConverter() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("id", "1");
		
		MvcResult result = mockMvc.perform(get("/admin/repositoryConverter").params(params)).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("admin/list"))
		.andExpect(model().attributeExists("user")).andReturn();
		
		User user = (User)result.getModelAndView().getModel().get("user");
		Assert.assertNotNull(user.getUsername());
	}
}
