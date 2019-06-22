package com.xsis.batch197.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {	
	@GetMapping(value="/login")
	public ModelAndView login() {
		return new ModelAndView("auth/login");
	}
	
	// untuk menangkap request setelah dipilih role dan company
	@PostMapping(value="/set-access")
	private String setAccess(@RequestParam("roleId") Long roleId, @RequestParam("companyId") Long companyId) {
		
		return "redirect:/home/index";
	}
	
	@GetMapping(value = "/register")
	public ModelAndView register() {
		return new ModelAndView("auth/register");
	}
}
