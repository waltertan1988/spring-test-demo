package com.my.demo.webapp.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.my.demo.webapp.test.controller.HtmlUtilControllerTest;
import com.my.demo.webapp.test.controller.LoginControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ LoginControllerTest.class, HtmlUtilControllerTest.class })
public class DemoWebappSuiteTest {

}
