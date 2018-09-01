package com.my.demo.base.response;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DemoExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		if(StringUtils.isEmpty(request.getHeader("x-requested-with"))){
			ModelAndView mv = new ModelAndView();
			mv.addObject("exception", ex);
			mv.setViewName("error");
	        return mv;
		}else{
			try {
				PrintWriter out = response.getWriter();
				DemoResponseBody sb = new DemoResponseBody();
				sb.setSuccess(false);
				sb.setExceptionMsg(ex.getMessage());
				sb.setExceptionStack(ex.getStackTrace().toString());
				out.print(new ObjectMapper().writeValueAsString(sb));
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}
	}
}
