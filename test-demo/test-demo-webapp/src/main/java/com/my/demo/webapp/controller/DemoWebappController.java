package com.my.demo.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.my.demo.admin.controller.AbstractAdminController;

@Controller
public class DemoWebappController extends AbstractAdminController{

	@RequestMapping(value = "/index", method = RequestMethod.GET )
	public String index() {
		return "admin/index";
	}
}
