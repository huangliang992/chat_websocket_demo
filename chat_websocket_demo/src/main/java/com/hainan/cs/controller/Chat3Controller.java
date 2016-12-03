package com.hainan.cs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hainan.cs.singleton.UserSingleton;

@Controller
@RequestMapping(value="/chat3")
public class Chat3Controller {
	@RequestMapping
	public ModelAndView chat3(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		UserSingleton user=UserSingleton.getInstance();
		request.getSession().setAttribute("username", user.getUsername());
		mav.addObject("username", user.getUsername());
		mav.setViewName("chat3");
		return mav;
	}
}
