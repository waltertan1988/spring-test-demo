package com.my.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.demo.service.MockDemoService;
import com.my.demo.service.ImplementedSubService1;
import com.my.demo.service.UnimplementedSubService1;

@Service
public class MockDemoServiceImpl implements MockDemoService {
	
	@Autowired
	private ImplementedSubService1 implementedSubService1;
	@Autowired(required=false)
	private UnimplementedSubService1 unimplementedSubService1;

	@Override
	public String saySthToSpringWhat(String sth, String what) {
		String res1 = unimplementedSubService1.appendComma(sth);
		String res2 = implementedSubService1.getSpringWhat(what);
		return res1 + res2;
	}
}
