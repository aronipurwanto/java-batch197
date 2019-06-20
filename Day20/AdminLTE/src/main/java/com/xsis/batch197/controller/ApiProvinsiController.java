package com.xsis.batch197.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.batch197.model.ProvinsiModel;
import com.xsis.batch197.repository.ProvinsiRepo;

@RestController
@RequestMapping("/api/")
public class ApiProvinsiController {
	@Autowired
	private ProvinsiRepo propRepo;
	
	@GetMapping("proninsi")
	private List<ProvinsiModel> list(){
		return propRepo.findAll();
	}
}
