package com.my.demo.admin.test;

import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({ @ContextConfiguration(locations = { "classpath*:/META-INF/spring/applicationContext-*-app.xml" }),
		@ContextConfiguration(locations = { "classpath*:/META-INF/spring/applicationContext-admin-mvc.xml" }) })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class AbstractControllerTest {

	protected <T> T json2Object(String jsonStr, TypeReference<T> valueTypeRef) throws JsonParseException, JsonMappingException, IOException{
		return new ObjectMapper().readValue(jsonStr, valueTypeRef);
	}
}
