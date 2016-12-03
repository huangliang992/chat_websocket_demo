package com.hainan.cs.controller;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hainan.cs.bean.UserBean;
import com.hainan.cs.dao.UserDaoImp;
import com.hainan.cs.singleton.UserSingleton;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	@RequestMapping()
	public ModelAndView login(){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("login");
		mav.addObject("tag",0);
		return mav;
	}
	@RequestMapping(value="/userlogin")
	public ModelAndView userLogin(String username,String password){
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring/application-config.xml");
		UserDaoImp udi=context.getBean(UserDaoImp.class);
		List<UserBean> userlist=udi.queryUser(username);
		ModelAndView mav=new ModelAndView();
		if(userlist.size()!=0){
			UserBean user=userlist.get(0);
			if(user.getPassword().equals(password)){
				UserSingleton us=UserSingleton.getInstance();
				us.setPassword(password);
				us.setUsername(username);
				us.setUserid(user.getId());
				mav.setViewName("redirect:/chat3");
			}else{
				mav.addObject("tag", 1);
				mav.setViewName("login");
			};
		}else {
			mav.addObject("tag", 1);
			mav.setViewName("login");
		}
		context.close();
		return mav;
	}
}
