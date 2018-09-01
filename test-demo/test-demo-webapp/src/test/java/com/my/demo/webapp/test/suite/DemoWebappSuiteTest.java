package com.my.demo.webapp.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.my.demo.webapp.test.controller.HtmlUtilControllerTest;
import com.my.demo.webapp.test.controller.LoginControllerTest;
import com.my.demo.webapp.test.controller.SpringSecurityTest;

@RunWith(Suite.class)
@SuiteClasses({ LoginControllerTest.class, HtmlUtilControllerTest.class, SpringSecurityTest.class })
public class DemoWebappSuiteTest {

}
