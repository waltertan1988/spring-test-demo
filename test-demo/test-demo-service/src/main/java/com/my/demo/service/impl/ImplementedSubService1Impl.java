package com.my.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.demo.service.ImplementedSubService1;
import com.my.demo.service.UnimplementedSubService2;

@Service
public class ImplementedSubService1Impl implements ImplementedSubService1 {

	@Autowired(required=false)
	private UnimplementedSubService2 unimplementedSubService2;
	
	@Override
	public String getSpringWhat(String what) {
		return "Spring " + unimplementedSubService2.getWhatAppendFullStop(what);
	}
}
