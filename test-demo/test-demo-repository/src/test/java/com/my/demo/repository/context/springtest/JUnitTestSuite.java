package com.my.demo.repository.context.springtest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Cases1Test.class, Cases2Test.class,
		Cases3Test.class, Cases4Test.class, Cases5Test.class,
		Cases6Test.class, Cases7Test.class,
		Cases8Test.class, Cases9Test.class, Cases10Test.class})
public class JUnitTestSuite {

}
