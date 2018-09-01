/**
 * DemoControllerTestSuite.java 2018年3月13日
 */
package com.my.demo.admin.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.my.demo.admin.test.controller.DemoControllerRawTest;
import com.my.demo.admin.test.controller.DemoControllerStandaloneSetupTest;
import com.my.demo.admin.test.controller.DemoControllerWebAppContextSetupTest;

/**
 * <p>
 * <b>DemoControllerTestSuite</b> is
 * </p>
 * 
 * @since 2018年3月13日
 * @author WT
 * @version $Id$
 */
@RunWith(Suite.class)
@SuiteClasses({ DemoControllerStandaloneSetupTest.class,
		DemoControllerWebAppContextSetupTest.class,
		DemoControllerRawTest.class })
public class DemoAdminSuiteTest {

}
