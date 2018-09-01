package com.my.demo.webapp.test.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.my.demo.webapp.test.AbstractWebappControllerTest;

public class HtmlUtilControllerTest extends AbstractWebappControllerTest {
	
	private final String BASE_HOST="localhost:8080";
	private final String BASE_CONTEXT_PATH = "/test-demo-webapp";
	private final String BASE_PREFIX = "http://" + BASE_HOST + BASE_CONTEXT_PATH;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	WebClient webClient;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
		this.webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc)
				.useMockMvcForHosts(BASE_HOST)
				.contextPath(BASE_CONTEXT_PATH).build();
		
		this.webClient.getOptions().setJavaScriptEnabled(true);
	}

	@Test
	public void test1HtmlUtil() throws Exception {
		HtmlPage indexPage = webClient.getPage(BASE_PREFIX + "/admin/index");
		String s = indexPage.asXml();
		Assert.assertNotNull(indexPage.asXml());
		HtmlAnchor a = indexPage.getAnchorByHref(BASE_CONTEXT_PATH + "/admin/getUser");
		HtmlPage getUserPage = a.click();// index页面跳转到getUser页面
		Assert.assertNotNull(getUserPage.asXml());
		
		HtmlForm form = getUserPage.getHtmlElementById("getUserForm");
		form.getInputByName("userRealName").setValueAttribute("张三");
		WebResponse wr = form.getOneHtmlElementByAttribute("input", "id", "searchBtn").click().getWebResponse();
		System.out.println(wr.getContentAsString());
	}
}
