package com.xsis.batch197.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.batch197.model.BaseModel;
import com.xsis.batch197.repository.XCompanyRepo;
import com.xsis.batch197.repository.XRoleRepo;

@Controller
public class AuthenticationController extends BaseModel {
	@Autowired
	private XRoleRepo roleRepo;
	
	@Autowired
	private XCompanyRepo comRepo;
	
	@GetMapping(value="/login")
	public ModelAndView login() {
		return new ModelAndView("auth/login");
	}

	@GetMapping(value = "/forgot-password")
	public ModelAndView forgot() {
		return new ModelAndView("auth/forgot-password");
	}
	
	// setelah login pilih role dan pilih company
	@GetMapping(value = "/select-role")
	public ModelAndView selectAccess() {
		ModelAndView view = new ModelAndView("auth/select-access");
		// add object to list
		view.addObject("listRole", this.roleRepo.findAll());
		view.addObject("listCompany", this.comRepo.findAll());
		
		return view;
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
