package com.my.demo.admin.test.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import com.my.demo.admin.controller.DemoController;
import com.my.demo.domain.User;
import com.my.demo.repository.UserRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)//无需指定SpringRunner
public class DemoControllerStandaloneSetupTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private DemoController demoController = new DemoController(); //new方式构造Controller
	@Mock
	private UserRepository userRepository; //构造Mock并由@InjectMocks所依赖

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();//Standalone
	}

	@Test
	public void test1DemoList() throws Exception {
		String inputUsername = "Walter";
		User user = new User();
		user.setUsername(inputUsername);
		when(userRepository.findByUsername(anyString())).thenReturn(user);
		
		ModelAndView mv = mockMvc.perform(get("/admin/list")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("admin/list"))
				.andExpect(model().attributeExists("user")).andReturn().getModelAndView();
		
		verify(userRepository, Mockito.times(1)).findByUsername(anyString());
		
		User outputUser = (User)mv.getModel().get("user");
		Assert.assertTrue(inputUsername.equals(outputUser.getUsername()));
	}
	
	@Test(expected = NestedServletException.class)
	public void test2ControllerException() throws Exception{
		mockMvc.perform(get("/admin/exception"));
	}
}
