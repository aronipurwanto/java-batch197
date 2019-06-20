package com.xsis.batch197.controller;

import com.xsis.batch197.model.UserModel;
import com.xsis.batch197.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
