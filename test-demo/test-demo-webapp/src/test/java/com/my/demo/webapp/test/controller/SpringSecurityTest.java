package com.my.demo.webapp.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(locations = { "classpath*:/META-INF/applicationContext-web-app.xml",
				"classpath*:/META-INF/applicationContext-security.xml" }),
		@ContextConfiguration(locations = { "classpath*:/META-INF/spring/applicationContext-admin-mvc.xml",
				"classpath*:/configs/applicationContext-web-mvc.xml" }) })
@TestExecutionListeners(listeners = { 
		ServletTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class, 
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, 
		WithSecurityContextTestExecutionListener.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringSecurityTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac)
						.apply(SecurityMockMvcConfigurers.springSecurity())
						.build();
	}

	@Test
	@WithMockUser(authorities = { "ERROR_AUTH_CODE" })
	public void test1Login() throws Exception {
		mockMvc.perform(get("/admin/index")).andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin123", authorities = { "ROLE_ADMIN" })
	public void test2Login() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		
		String[] expected = new String[] {"admin", "admin123"};
		String[] actuals = new String[] {user.getUsername(), user.getPassword()};
		Assert.assertArrayEquals(expected, actuals);
		Assert.assertTrue(user.getAuthorities().size()>0);
		
		mockMvc.perform(get("/admin/index")).andExpect(status().isOk());
	}
}
