package com.xsis.batch197.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.springframework.web.servlet.ModelAndView;

import com.xsis.batch197.model.XBiodataModel;
import com.xsis.batch197.model.XEducationLevelModel;
import com.xsis.batch197.model.XRiwayatPendidikanModel;
import com.xsis.batch197.model.XTimePeriodeModel;
import com.xsis.batch197.repository.XBiodataRepo;
import com.xsis.batch197.repository.XEducationLevelRepo;
import com.xsis.batch197.repository.XRiwayatPendidikanRepo;
import com.xsis.batch197.repository.XTimePeriodeRepo;

@Controller
public class PendidikanController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PendidikanController.class);

	@Autowired
	private XBiodataRepo bioRepo;
	
	@Autowired
	private XEducationLevelRepo eduRepo;

	@Autowired
	private XRiwayatPendidikanRepo penRepo;

	@GetMapping(value = "/pelamar/pendidikan/{bid}")
	private ModelAndView index(@PathVariable("bid") Long biodataId) {
		// view sertifkasi
		ModelAndView view = new ModelAndView("pendidikan/index");
		// get biodata Id
		XBiodataModel biodata = this.bioRepo.findById(biodataId).orElse(null);
		view.addObject("biodata", biodata);

		// get pendidikan
		List<XRiwayatPendidikanModel> listPendidikan = this.penRepo.findActiveByBiodataId(biodataId);
		view.addObject("listPendidikan", listPendidikan);
		return view;
	}

	@GetMapping(value = "/pendidikan/add/{bid}") // bid sebagai vaiable biodataId
	public ModelAndView create(@PathVariable("bid") Long biodataId) {
		// menampilkan view dari folder pendidikan file _form.html
		ModelAndView view = new ModelAndView("pendidikan/_form");
		// membuat object pendidikan model
		XRiwayatPendidikanModel pendidikan = new XRiwayatPendidikanModel();
		// set biodata id
		pendidikan.setBiodataId(biodataId);

		Calendar date = new GregorianCalendar();
		Integer currentYear = date.get(Calendar.YEAR);

		List<Integer> listStartYear = new ArrayList<Integer>();
		for (int i = currentYear - 20; i <= currentYear; i++) {
			listStartYear.add(i);
		}

		List<XEducationLevelModel> listEdu = this.eduRepo.findAll();
		// add object pendidikan
		view.addObject("pendidikan", pendidikan);
		// add object tahun start
		view.addObject("listStartYear", listStartYear);
		// add object listTime
		view.addObject("listEdu", listEdu);
		return view;
	}

	@PostMapping(value = "/pendidikan/save")
	public ModelAndView save(@Valid @ModelAttribute("pendidikan") XRiwayatPendidikanModel pendidikan,
			BindingResult result) {
		// menampilkan view dari folder pendidikan file _form.html
		ModelAndView view = new ModelAndView("pendidikan/_form");

		if (result.hasErrors()) {
			logger.info("save biodata error");

			// add object pendidikan dengan error nya ke view
			view.addObject("pendidikan", pendidikan);
		} else {
			// set user id
			pendidikan.setCreatedBy(this.getAbuid());
			pendidikan.setCreatedOn(new Date());
			// simpan ke repo
			penRepo.save(pendidikan);
			// add object baru tanpa error
			view.addObject("pendidikan", new XRiwayatPendidikanModel());
		}
		return view;
	}

	@GetMapping(value = "/pendidikan/list/{bid}")
	private ModelAndView list(@PathVariable("bid") Long biodataId) {
		// view sertifkasi
		ModelAndView view = new ModelAndView("pendidikan/_list");
		// get biodata Id
		XBiodataModel biodata = this.bioRepo.findById(biodataId).orElse(null);
		view.addObject("biodata", biodata);

		// get pendidikan
		List<XRiwayatPendidikanModel> listPendidikan = this.penRepo.findActiveByBiodataId(biodataId);
		view.addObject("listPendidikan", listPendidikan);
		return view;
	}

	@GetMapping(value = "/pendidikan/hapus/{pid}")
	private ModelAndView hapus(@PathVariable("pid") Long pid) {
		// view sertifkasi
		ModelAndView view = new ModelAndView("pendidikan/_hapus");
		// get pendidikan
		XRiwayatPendidikanModel pendidikan = this.penRepo.findById(pid).orElse(null);
		view.addObject("pendidikan", pendidikan);
		return view;
	}

	@PostMapping(value = "/pendidikan/remove")
	private ModelAndView remove(@ModelAttribute("pendidikan") XRiwayatPendidikanModel pendidikan) {
		// get pendidikan
		XRiwayatPendidikanModel item = this.penRepo.findById(pendidikan.getId()).orElse(null);
		pendidikan.setIsDelete(1);
		this.penRepo.save(pendidikan);

		// view sertifkasi
		ModelAndView view = new ModelAndView("pendidikan/_hapus");
		view.addObject("pendidikan", pendidikan);
		return view;
	}
	
	@GetMapping(value = "/pendidikan/ubah/{pid}") // bid sebagai vaiable biodataId
	public ModelAndView edit(@PathVariable("pid") Long pid) {
		// menampilkan view dari folder pendidikan file _form.html
		ModelAndView view = new ModelAndView("pendidikan/_form");
		// membuat object pendidikan model
		XRiwayatPendidikanModel pendidikan = this.penRepo.findById(pid).orElse(null);

		Calendar date = new GregorianCalendar();
		Integer currentYear = date.get(Calendar.YEAR);
		List<Integer> listStartYear = new ArrayList<Integer>();
		for (int i = currentYear - 20; i <= currentYear; i++) {
			listStartYear.add(i);
		}

		List<XEducationLevelModel> listEdu = this.eduRepo.findAll();
		// add object pendidikan
		view.addObject("pendidikan", pendidikan);
		// add object tahun start
		view.addObject("listStartYear", listStartYear);
		// add object listTime
		view.addObject("listEdu", listEdu);
		return view;
	}
}
