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

import com.xsis.batch197.model.KecamatanModel;
import com.xsis.batch197.model.KotaModel;
import com.xsis.batch197.model.ProvinsiModel;
import com.xsis.batch197.repository.KecamatanRepo;
import com.xsis.batch197.repository.KotaRepo;

@Controller
@RequestMapping(value = "/kecamatan")
public class KecamatanController {
	private static final Logger logger = LoggerFactory.getLogger(KecamatanController.class);

	@Autowired
	private KecamatanRepo repo;

	@Autowired
	private KotaRepo repoKota;

	// method untuk generate kode kecamatan automatis
	private String getKode() {
		String result = "";
		if (repo.getMaxKode() != null) {
			result = repo.getMaxKode().split("-")[1];
			result = "KEC-" + String.format("%05d", (Integer.parseInt(result) + 1));
		} else {
			result = "KEC-00001";
		}
		return result;
	}

	// #1. index => list data
	@GetMapping(value = "/index")
	public ModelAndView index() {
		// buat object view
		ModelAndView view = new ModelAndView("kecamatan/index");
		return view;
	}

	// #1. index => list data
	@GetMapping(value = "/list")
	public ModelAndView list() {
		// buat object view
		ModelAndView view = new ModelAndView("kecamatan/list");
		// load data kecamatan via repo, disimpan kedalam list
		List<KecamatanModel> listkecamatan = repo.findAll();
		// lemparkan data ke view, list object baru, datanya listkecamatan
		view.addObject("list", listkecamatan);
		return view;
	}

	// #1. index => list data
	@GetMapping(value = "/list/{key}")
	public ModelAndView list(@PathVariable("key") String key) {
		// buat object view
		ModelAndView view = new ModelAndView("kecamatan/list");
		// load data kecamatan via repo, disimpan kedalam list
		List<KecamatanModel> listkecamatan = repo.search(key);
		// lemparkan data ke view, list object baru, datanya listkecamatan
		view.addObject("list", listkecamatan);
		return view;
	}

	// #2. Membuat Form Add kecamatan
	@GetMapping(value = "/add")
	public ModelAndView create() {
		// buat object view
		ModelAndView view = new ModelAndView("kecamatan/create");
		// membuat object kecamatan yg akan dikirim ke view
		// object kecamatan adalah new object dari KecamatanModel
		KecamatanModel kecamatan = new KecamatanModel();
		// isi kdkecamatan dengan method getKode()
		kecamatan.setKdKecamatan(getKode());
		view.addObject("kecamatan", kecamatan);

		// mengambil data propinsi yang sudah ada
		List<KotaModel> listKota = repoKota.findAll();
		// object listProp akan kita kirim ke view, agar pilihan provinsiId bisa terisi
		// datanya
		view.addObject("listKota", listKota);
		return view;
	}

	// #3. Menangkap data dari form
	@PostMapping(value = "/save")
	public ModelAndView save(@Valid @ModelAttribute("kecamatan") KecamatanModel kecamatan, BindingResult result) {
		if (result.hasErrors()) {
			logger.info("save kecamatan error");
		} else {
			repo.save(kecamatan);
		}

		ModelAndView view = new ModelAndView("kecamatan/create");
		view.addObject("kecamatan", kecamatan);
		return view;
	}

	// #2. Membuat Form Add kecamatan
	@GetMapping(value = "/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		// buat object view
		ModelAndView view = new ModelAndView("kecamatan/update");
		// mengambil data dahulu dari database via repository
		KecamatanModel kecamatan = repo.findById(id).orElse(new KecamatanModel());
		// membuat object kecamatan yg akan dikirim ke view
		// object kecamatan adalah new object dari KecamatanModel
		view.addObject("kecamatan", kecamatan);

		// mengambil data propinsi yang sudah ada
		List<KotaModel> listKota = repoKota.findAll();
		// object listProp akan kita kirim ke view, agar pilihan provinsiId bisa terisi
		// datanya
		view.addObject("listKota", listKota);
		return view;
	}

	// #3. Menangkap data dari form
	@PostMapping(value = "/update")
	public ModelAndView update(@Valid @ModelAttribute("kecamatan") KecamatanModel kecamatan, BindingResult result) {
		if (result.hasErrors()) {
			logger.info("save kecamatan error");
		} else {
			repo.save(kecamatan);
		}

		ModelAndView view = new ModelAndView("kecamatan/update");
		view.addObject("kecamatan", kecamatan);
		return view;
	}

	// #4. Membuat Form Add kecamatan
	@GetMapping(value = "/detail/{id}")
	public ModelAndView detail(@PathVariable("id") Long id) {
		// buat object view
		ModelAndView view = new ModelAndView("kecamatan/detail");
		// mengambil data dahulu dari database via repository
		KecamatanModel kecamatan = repo.findById(id).orElse(new KecamatanModel());
		// membuat object kecamatan yg akan dikirim ke view
		// object kecamatan adalah new object dari KecamatanModel
		view.addObject("kecamatan", kecamatan);
		return view;
	}

	// #4. Membuat Form Add kecamatan
	@GetMapping(value = "/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		// buat object view
		ModelAndView view = new ModelAndView("kecamatan/delete");
		// mengambil data dahulu dari database via repository
		KecamatanModel kecamatan = repo.findById(id).orElse(new KecamatanModel());
		// membuat object kecamatan yg akan dikirim ke view
		// object kecamatan adalah new object dari KecamatanModel
		view.addObject("kecamatan", kecamatan);
		return view;
	}

	// #3. Menangkap data dari form
	@PostMapping(value = "/remove")
	public ModelAndView remove(@ModelAttribute("kecamatan") KecamatanModel kecamatan) {
		// remove data dari database via repo
		repo.delete(kecamatan);
		// membuat object view
		ModelAndView view = new ModelAndView("kecamatan/create");
		// redirect to index
		return view;
	}
}
