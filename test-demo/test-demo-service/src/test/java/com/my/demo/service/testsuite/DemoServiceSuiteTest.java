package com.my.demo.service.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.my.demo.service.test.MockitoDiTest;

@RunWith(Suite.class)
@SuiteClasses({ MockitoDiTest.class })
public class DemoServiceSuiteTest {

}
