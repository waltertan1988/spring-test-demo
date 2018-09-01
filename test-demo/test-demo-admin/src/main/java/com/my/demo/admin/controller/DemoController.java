package com.my.demo.admin.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.demo.domain.User;
import com.my.demo.repository.UserRepository;

@Controller
public class DemoController extends AbstractAdminController{
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		User user = userRepository.findByUsername("walter");
		model.addAttribute("user", user);
		return "admin/list";
	}
	
	@RequestMapping(value = "/repositoryConverter", method = RequestMethod.GET)
	public String repositoryConverter(User user, Model model) {
		model.addAttribute("user", user);
		return "admin/list";
	}
	
	@RequestMapping(value = "/exception", method = RequestMethod.GET)
	public String exception(Model model){
		int a=1/0;
		model.addAttribute("a", a);
		return "admin/getUser";
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public String getUser(){
		return "admin/getUser";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserData", method = RequestMethod.POST)
	public List<User> getUserData(String userRealName){
		return userRepository.findByUserRealName(userRealName);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserDataRest/{userRealName}", method = RequestMethod.POST)
	public List<User> getUserDataRest(@PathVariable("userRealName") String userRealName) throws UnsupportedEncodingException{
		userRealName = URLDecoder.decode(userRealName, "UTF-8");
		return userRepository.findByUserRealName(userRealName);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserDataByReq", method = RequestMethod.POST)
	public List<User> getUserDataByReq(HttpServletRequest request, ServletResponse response){
		HttpSession session = request.getSession();
		ServletContext sc = request.getServletContext();
		
		return userRepository.findByUserRealName(request.getParameter("userRealName"));
	}
}
