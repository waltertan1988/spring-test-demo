package com.my.demo.repository.springtest.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.my.demo.repository.junit.testsuite.JUnitIntegrationSuiteTest;
import com.my.demo.repository.springtest.basic.DemoRepositoryTest;
import com.my.demo.repository.springtest.extend.DemoRepository1Test;
import com.my.demo.repository.springtest.extend.DemoRepository2Test;
import com.my.demo.repository.springtest.transaction.basic.DemoTransactionTest;
import com.my.demo.repository.springtest.transaction.extend.DemoTransactionExtendTest;

@RunWith(Suite.class)
@SuiteClasses({ JUnitIntegrationSuiteTest.class, DemoRepositoryTest.class,
		DemoRepository1Test.class, DemoRepository2Test.class,
		DemoTransactionTest.class, DemoTransactionExtendTest.class })
public class DemoRepositorySuiteTest {

}
