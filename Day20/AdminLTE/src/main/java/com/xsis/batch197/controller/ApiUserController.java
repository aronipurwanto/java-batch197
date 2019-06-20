package com.xsis.batch197.controller;

import com.xsis.batch197.model.UserModel;
import com.xsis.batch197.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ApiUserController extends BaseController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "user")
    public List<UserModel> listUser(){
        return userRepo.findAll();
    }
    
    @GetMapping(value = "user/get/{id}")
    public UserModel getUser(@PathVariable("id") Long id){
        return userRepo.findById(id).orElse(new UserModel());
    }
    
    @PostMapping(value="user/{username}")
    public UserModel lockUser(@PathVariable("username") String username) {
    	// get data lewat repo
    	UserModel user = this.userRepo.findByUsername(username);
    	if(user != null) {
    		// mengganti user locked => 1
    		user.setIsLocked(1);
    		// simpan user
    		this.userRepo.save(user);
    	}
    	// direturn
    	return user;
    }
}
