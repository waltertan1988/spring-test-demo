package com.my.demo.webapp.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.my.demo.webapp.test.AbstractWebappControllerTest;

public class LoginControllerTest extends AbstractWebappControllerTest{

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void test1Login() throws Exception {
		mockMvc.perform(get("/admin/index")).andExpect(status().isOk());
	}
	
	@Test
	public void test2DemoList() throws Exception {
		mockMvc.perform(get("/admin/list")).andExpect(status().isOk())
				.andExpect(model().attributeExists("user"));
	}
}
