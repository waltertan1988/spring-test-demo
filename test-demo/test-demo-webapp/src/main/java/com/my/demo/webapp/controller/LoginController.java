package com.my.demo.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET )
	public String login(@RequestParam(required=false, defaultValue="false") boolean error, Model model) {
		if(error){
			model.addAttribute("error", "登录失败");
		}
		return "login/login";
	}
	
	@RequestMapping(value = "/logoutPostPage", method = RequestMethod.GET )
	public String logoutPostPage() {
		return "login/logoutPostPage";
	}
}
