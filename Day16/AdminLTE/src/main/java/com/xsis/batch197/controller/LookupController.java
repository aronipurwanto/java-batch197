package com.xsis.batch197.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.batch197.model.LookupModel;
import com.xsis.batch197.repository.LookupRepo;

@Controller
@RequestMapping(value="/lookup")
public class LookupController {
	private static final Logger logger = LoggerFactory.getLogger(LookupController.class);
	
	@Autowired
	private LookupRepo repo;
	
	//#1. index => list data
	@GetMapping(value="/index")
	public ModelAndView index() {
		// buat object view
		ModelAndView view = new ModelAndView("lookup/index");
		// load data lookup via repo, disimpan kedalam list
		List<LookupModel> listLookup = repo.findAll();
		// lemparkan data ke view, list object baru, datanya listLookup
		view.addObject("list", listLookup);
		return view;
	}
}
