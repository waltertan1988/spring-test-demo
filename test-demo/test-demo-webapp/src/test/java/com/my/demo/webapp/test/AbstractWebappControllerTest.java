package com.my.demo.webapp.test;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration("classpath*:/META-INF/applicationContext-web-app.xml"),
		@ContextConfiguration(locations = {
				"classpath*:/META-INF/spring/applicationContext-admin-mvc.xml",
				"classpath*:/configs/applicationContext-web-mvc.xml" }) })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class AbstractWebappControllerTest {

}
