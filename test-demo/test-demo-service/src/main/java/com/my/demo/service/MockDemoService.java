package com.my.demo.service;


public interface MockDemoService {
	
	/**
	 * 向Spring [what]说[sth]，并带上标点符号
	 * @param sth（如Hello/Hi等）
	 * @param what(Test/MVC等)
	 * @return [sth], [Spring] what.
	 */
	public String saySthToSpringWhat(String sth, String what);
}
