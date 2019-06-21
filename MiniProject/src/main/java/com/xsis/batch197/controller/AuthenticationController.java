package com.xsis.batch197.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.batch197.model.BaseModel;


@Controller
public class AuthenticationController extends BaseModel{

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	
	public ModelAndView login() 
		{
		ModelAndView modelAndView = new ModelAndView();
//		List<XAddressBookModel> address = repo.search(email, abpwd);
		modelAndView.setViewName("login");
		return modelAndView;
	
}

	@RequestMapping(value = "/lupa-password", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("lupa-password"); // resources/template/register.html
		return modelAndView;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index"); // resources/template/index.html
		return modelAndView;
	}

}
