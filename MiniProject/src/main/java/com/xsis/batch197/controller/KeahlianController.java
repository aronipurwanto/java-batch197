package com.xsis.batch197.controller;

import java.util.Date;
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
import com.xsis.batch197.model.XKeahlianModel;
import com.xsis.batch197.model.XSkillLevelModel;
import com.xsis.batch197.repository.XBiodataRepo;
import com.xsis.batch197.repository.XKeahlianRepo;
import com.xsis.batch197.repository.XSkillLevelRepo;

@Controller
public class KeahlianController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(KeahlianController.class);

	@Autowired
	private XBiodataRepo bioRepo;

	@Autowired
	private XKeahlianRepo sertRepo;

	@Autowired
	private XSkillLevelRepo skillRepo;

	@GetMapping(value = "/pelamar/keahlian/{bid}")
	private ModelAndView index(@PathVariable("bid") Long biodataId) {
		// view sertifkasi
		ModelAndView view = new ModelAndView("keahlian/index");
		// get biodata Id
		XBiodataModel biodata = this.bioRepo.findById(biodataId).orElse(null);
		view.addObject("biodata", biodata);

		// get keahlian
		List<XKeahlianModel> listkeahlian = this.sertRepo.findByBiodataId(biodataId);
		view.addObject("listkeahlian", listkeahlian);
		return view;
	}

	@GetMapping(value = "/keahlian/add/{bid}") // bid sebagai vaiable biodataId
	public ModelAndView create(@PathVariable("bid") Long biodataId) {
		// menampilkan view dari folder keahlian file _form.html
		ModelAndView view = new ModelAndView("keahlian/_form");
		// membuat object keahlian model
		XKeahlianModel keahlian = new XKeahlianModel();
		// set biodata id
		keahlian.setBiodataId(biodataId);

		List<XSkillLevelModel> listSkill = this.skillRepo.findAll();
		// add object keahlian
		view.addObject("keahlian", keahlian);
		// add object list mothn
		view.addObject("listSkill", listSkill);
		return view;
	}

	@PostMapping(value = "/keahlian/save")
	public ModelAndView save(@Valid @ModelAttribute("keahlian") XKeahlianModel keahlian, BindingResult result) {
		// menampilkan view dari folder keahlian file _form.html
		ModelAndView view = new ModelAndView("keahlian/_form");
		
		List<XSkillLevelModel> listSkill = this.skillRepo.findAll();
		// add object list mothn
		view.addObject("listSkill", listSkill);
		
		if (result.hasErrors()) {
			logger.info("save biodata error");

			// add object keahlian dengan error nya ke view
			view.addObject("keahlian", keahlian);
		} else {
			// set user id
			keahlian.setCreatedBy(this.getAbuid());
			keahlian.setCreatedOn(new Date());
			// simpan ke repo
			sertRepo.save(keahlian);
			// add object baru tanpa error
			view.addObject("keahlian", new XKeahlianModel());
		}
		return view;
	}

	@GetMapping(value = "/keahlian/list/{bid}")
	private ModelAndView list(@PathVariable("bid") Long biodataId) {
		// view sertifkasi
		ModelAndView view = new ModelAndView("keahlian/_list");
		// get biodata Id
		XBiodataModel biodata = this.bioRepo.findById(biodataId).orElse(null);
		view.addObject("biodata", biodata);

		// get keahlian
		List<XKeahlianModel> listkeahlian = this.sertRepo.findByBiodataId(biodataId);
		view.addObject("listkeahlian", listkeahlian);
		return view;
	}

	@GetMapping(value = "/keahlian/hapus/{sid}")
	private ModelAndView hapus(@PathVariable("sid") Long sid) {
		// view sertifkasi
		ModelAndView view = new ModelAndView("keahlian/_hapus");
		// get keahlian
		XKeahlianModel keahlian = this.sertRepo.findById(sid).orElse(null);
		view.addObject("keahlian", keahlian);
		return view;
	}

	@PostMapping(value = "/keahlian/remove")
	private ModelAndView remove(@ModelAttribute("keahlian") XKeahlianModel keahlian) {
		// get keahlian
		XKeahlianModel item = this.sertRepo.findById(keahlian.getId()).orElse(null);

		// set delete
		item.setDeletedOn(new Date());
		item.setDeletedBy(this.getAbuid());
		item.setIsDelete(1);
		this.sertRepo.save(item);

		// view sertifkasi
		ModelAndView view = new ModelAndView("keahlian/_hapus");
		view.addObject("keahlian", item);
		return view;
	}

	@GetMapping(value = "/keahlian/ubah/{sid}") // bid sebagai vaiable biodataId
	public ModelAndView edit(@PathVariable("sid") Long sid) {
		// menampilkan view dari folder keahlian file _form.html
		ModelAndView view = new ModelAndView("keahlian/_form");
		// membuat object keahlian model
		XKeahlianModel keahlian = this.sertRepo.findById(sid).orElse(null);

		List<XSkillLevelModel> listSkill = this.skillRepo.findAll();
		// add object keahlian
		view.addObject("keahlian", keahlian);
		// add object list mothn
		view.addObject("listSkill", listSkill);
		return view;
	}
}
