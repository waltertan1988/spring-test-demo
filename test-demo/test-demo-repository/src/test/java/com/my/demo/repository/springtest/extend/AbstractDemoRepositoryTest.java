package com.my.demo.repository.springtest.extend;

import javax.persistence.Table;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath*:/META-INF/spring/applicationContext-*-app.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class AbstractDemoRepositoryTest {
	
	/**
	 * 获取domain对应的table名
	 * @param domain
	 * @return
	 */
	protected String getTableNameFromDomain(Class<?> clz){
		if(!clz.isAnnotationPresent(Table.class)){
			throw new RuntimeException(clz.getName()+"不是domain类");
		}
		return clz.getAnnotation(Table.class).name();
	}
}
