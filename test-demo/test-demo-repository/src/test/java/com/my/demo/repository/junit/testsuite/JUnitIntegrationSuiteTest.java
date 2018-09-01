package com.my.demo.repository.junit.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.my.demo.repository.junit.testcase.Cases2Test;

@RunWith(Suite.class)
@SuiteClasses({JUnitSuiteTest.class, Cases2Test.class})
public class JUnitIntegrationSuiteTest {
	
}
