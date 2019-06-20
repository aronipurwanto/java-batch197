package com.xsis.batch197.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.batch197.model.BiodataModel;
import com.xsis.batch197.model.LookupModel;
import com.xsis.batch197.model.ProvinsiModel;
import com.xsis.batch197.repository.BiodataRepo;
import com.xsis.batch197.repository.LookupRepo;
import com.xsis.batch197.repository.ProvinsiRepo;

@Controller
@RequestMapping(value = "/biodata")
public class BiodataController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BiodataController.class);

	@Autowired
	private BiodataRepo repo;

	@Autowired
	private LookupRepo lookupRepo;

	@Autowired
	private ProvinsiRepo repoProp;

	// #1. index => list data
	@GetMapping(value = "/index")
	public ModelAndView index() {
		// buat object view
		ModelAndView view = new ModelAndView("biodata/index");
		return view;
	}

	// #1. index => list data
	@GetMapping(value = "/list")
	public ModelAndView list() {
		// buat object view
		ModelAndView view = new ModelAndView("biodata/item");
		// load data biodata via repo, disimpan kedalam list
		List<BiodataModel> listbiodata = repo.findAll();
		// lemparkan data ke view, list object baru, datanya listbiodata
		view.addObject("list", listbiodata);
		return view;
	}

	@GetMapping(value = "/list/{key}")
	public ModelAndView list(@PathVariable("key") String key) {
		// buat object view
		ModelAndView view = new ModelAndView("biodata/list");
		// load data biodata via repo, disimpan kedalam list
		List<BiodataModel> listbiodata = repo.search(key);
		// lemparkan data ke view, list object baru, datanya listbiodata
		view.addObject("list", listbiodata);
		return view;
	}

	// #2. Membuat Form Add biodata
	@GetMapping(value = "/add")
	public ModelAndView create() {
		// buat object view
		ModelAndView view = new ModelAndView("biodata/create");
		// membuat object biodata yg akan dikirim ke view
		// object biodata adalah new object dari BiodataModel
		BiodataModel biodata = new BiodataModel();
		view.addObject("biodata", biodata);
		// add list jk
		List<LookupModel> listJk = this.lookupRepo.findByType("JenisKelamin");
		view.addObject("listJk", listJk);

		// add list agama
		List<LookupModel> listAgama = this.lookupRepo.findByType("Agama");
		view.addObject("listAgama", listAgama);

		// add list Gol. Darah
		List<LookupModel> listGD = this.lookupRepo.findByType("GolDarah");
		view.addObject("listGD", listGD);

		// add list Gol. Darah
		List<LookupModel> listSN = this.lookupRepo.findByType("StatusNikah");
		view.addObject("listSN", listSN);

		// mengambil data propinsi yang sudah ada
		List<ProvinsiModel> listProp = repoProp.findAll();
		// object listProp akan kita kirim ke view, agar pilihan provinsiId bisa terisi
		// datanya
		view.addObject("listProp", listProp);
		return view;
	}

	// #3. Menangkap data dari form
	@PostMapping(value = "/save")
	public ModelAndView save(@Valid @ModelAttribute("biodata") BiodataModel biodata, BindingResult result) {
		if (result.hasErrors()) {
			logger.info("save biodata error");
		} else {
			repo.save(biodata);
		}

		ModelAndView view = new ModelAndView("biodata/create");
		view.addObject("biodata", biodata);
		return view;
	}

	// #2. Membuat Form Add biodata
	@GetMapping(value = "/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		// buat object view
		ModelAndView view = new ModelAndView("biodata/update");
		// mengambil data dahulu dari database via repository
		BiodataModel biodata = repo.findById(id).orElse(new BiodataModel());
		// membuat object biodata yg akan dikirim ke view
		// object biodata adalah new object dari BiodataModel
		view.addObject("biodata", biodata);

		// add list jk
		List<LookupModel> listJk = this.lookupRepo.findByType("JenisKelamin");
		view.addObject("listJk", listJk);

		// add list agama
		List<LookupModel> listAgama = this.lookupRepo.findByType("Agama");
		view.addObject("listAgama", listAgama);

		// add list Gol. Darah
		List<LookupModel> listGD = this.lookupRepo.findByType("GolDarah");
		view.addObject("listGD", listGD);

		// add list Gol. Darah
		List<LookupModel> listSN = this.lookupRepo.findByType("StatusNikah");
		view.addObject("listSN", listSN);

		// mengambil data propinsi yang sudah ada
		List<ProvinsiModel> listProp = repoProp.findAll();
		// object listProp akan kita kirim ke view, agar pilihan provinsiId bisa terisi
		// datanya
		view.addObject("listProp", listProp);
		return view;
	}

	// #3. Menangkap data dari form
	@PostMapping(value = "/update")
	public ModelAndView update(@Valid @ModelAttribute("biodata") BiodataModel biodata, BindingResult result) {
		if (result.hasErrors()) {
			logger.info("save biodata error");
		} else {
			repo.save(biodata);
		}

		ModelAndView view = new ModelAndView("biodata/update");
		view.addObject("biodata", biodata);
		return view;
	}

	// #4. Membuat Form Add biodata
	@GetMapping(value = "/detail/{id}")
	public ModelAndView detail(@PathVariable("id") Long id) {
		// buat object view
		ModelAndView view = new ModelAndView("biodata/detail");
		// mengambil data dahulu dari database via repository
		BiodataModel biodata = repo.findById(id).orElse(new BiodataModel());
		// membuat object biodata yg akan dikirim ke view
		// object biodata adalah new object dari BiodataModel
		view.addObject("biodata", biodata);
		return view;
	}

	// #4. Membuat Form Add biodata
	@GetMapping(value = "/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		// buat object view
		ModelAndView view = new ModelAndView("biodata/delete");
		// mengambil data dahulu dari database via repository
		BiodataModel biodata = repo.findById(id).orElse(new BiodataModel());
		// membuat object biodata yg akan dikirim ke view
		// object biodata adalah new object dari BiodataModel
		view.addObject("biodata", biodata);
		return view;
	}

	// #3. Menangkap data dari form
	@PostMapping(value = "/remove")
	public ModelAndView remove(@ModelAttribute("biodata") BiodataModel biodata) {
		// remove data dari database via repo
		repo.delete(biodata);
		// membuat object view
		ModelAndView view = new ModelAndView("biodata/delete");
		view.addObject("biodata", biodata);
		// redirect to index
		return view;
	}
}
