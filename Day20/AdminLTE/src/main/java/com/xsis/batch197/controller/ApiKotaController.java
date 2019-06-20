package com.xsis.batch197.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.batch197.model.KotaModel;
import com.xsis.batch197.repository.KotaRepo;

@RestController
@RequestMapping("/api/")
public class ApiKotaController {
	@Autowired
	private KotaRepo repo;
	
	@GetMapping("kota/{id}")
	private List<KotaModel> list(@PathVariable("id") Long id){
		return repo.findByProvinsiId(id);
	}
}
