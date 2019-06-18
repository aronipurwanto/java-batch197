package com.xsis.batch197.controller;

import com.xsis.batch197.model.UserModel;
import com.xsis.batch197.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
	@GetMapping(value="/")
	public String index() {
		return "home/index";
	}

	@GetMapping(value = "/login")
	public String login(){return  "home/login";}
}
