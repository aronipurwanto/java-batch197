package com.xsis.batch197.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.xsis.batch197.model.UserModel;
import com.xsis.batch197.repository.UserRepo;

public class BaseController {
	
	@Autowired
	private UserRepo userRepo;
	
	public String getUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String result =" -";
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			result=auth.getName();
		}
		return result;
	}
	
	public Long getUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long result= Long.MIN_VALUE;
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			result= this.userRepo.findByUsername(auth.getName()).getId();
		}		
		return result;
	}
	
	public UserModel getUserModel() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserModel result =new UserModel();
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			result= this.userRepo.findByUsername(auth.getName());
		}		
		return result;
	}
}
