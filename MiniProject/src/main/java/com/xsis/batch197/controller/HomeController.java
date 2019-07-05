package com.xsis.batch197.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.batch197.repository.XCompanyRepo;
import com.xsis.batch197.repository.XRoleRepo;

@Controller
public class HomeController {
	@GetMapping(value = "/")
	public ModelAndView index() {
		return new ModelAndView("home/index");
	}

	@GetMapping(value = "/home/index")
	public ModelAndView dashbord() {
		return new ModelAndView("home/index");
	}
}
