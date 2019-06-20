package com.xsis.batch197.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.batch197.model.KecamatanModel;
import com.xsis.batch197.repository.KecamatanRepo;

@RestController
@RequestMapping("/api/")
public class ApiKecatamanController {
	@Autowired
	private KecamatanRepo repo;
	
	@GetMapping("kecamatan/{id}")
	private List<KecamatanModel> list(@PathVariable("id") Long id){
		return repo.findByKotaId(id);
	}
}
