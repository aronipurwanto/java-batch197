package com.xsis.batch197.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.xsis.batch197.model.BaseModel;

@Controller
public class AuthenticationController extends BaseModel {

	@GetMapping(value="/login")
	public String login() {
		return "auth/login";
	}

	@GetMapping(value = "/forgot-password")
	public String forgot() {
		return "auth/forgot-password";
	}
	
	@GetMapping(value = "/select-role")
	public String selectAccess() {
		return "auth/select-access";
	}
	
	@GetMapping(value = "/register")
	public String register() {
		return "auth/register";
	}
}
