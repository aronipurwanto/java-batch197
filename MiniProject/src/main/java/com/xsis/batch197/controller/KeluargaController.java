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
import com.xsis.batch197.model.XFamilyRelationModel;
import com.xsis.batch197.model.XFamilyTreeTypeModel;
import com.xsis.batch197.model.XKeluargaModel;
import com.xsis.batch197.repository.XBiodataRepo;
import com.xsis.batch197.repository.XEducationLevelRepo;
import com.xsis.batch197.repository.XFamilyRelationRepo;
import com.xsis.batch197.repository.XFamilyTreeTypeRepo;
import com.xsis.batch197.repository.XKeluargaRepo;

@Controller
public class KeluargaController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(KeluargaController.class);

	@Autowired
	private XBiodataRepo bioRepo;

	@Autowired
	private XKeluargaRepo sertRepo;

	@Autowired
	private XFamilyRelationRepo relRepo;

	@Autowired
	private XFamilyTreeTypeRepo treeRepo;

	@Autowired
	private XEducationLevelRepo eduRepo;

	@GetMapping(value = "/pelamar/keluarga/{bid}")
	private ModelAndView index(@PathVariable("bid") Long biodataId) {
		// view sertifkasi
		ModelAndView view = new ModelAndView("keluarga/index");
		// get biodata Id
		XBiodataModel biodata = this.bioRepo.findById(biodataId).orElse(null);
		view.addObject("biodata", biodata);

		// get keluarga
		List<XKeluargaModel> listkeluarga = this.sertRepo.findByBiodataId(biodataId);
		view.addObject("listkeluarga", listkeluarga);
		return view;
	}

	@GetMapping(value = "/keluarga/add/{bid}") // bid sebagai vaiable biodataId
	public ModelAndView create(@PathVariable("bid") Long biodataId) {
		// menampilkan view dari folder keluarga file _form.html
		ModelAndView view = new ModelAndView("keluarga/_form");
		// membuat object keluarga model
		XKeluargaModel keluarga = new XKeluargaModel();
		// set biodata id
		keluarga.setBiodataId(biodataId);

		List<XFamilyRelationModel> listRel = this.relRepo.findAll();
		List<XFamilyTreeTypeModel> listTree = this.treeRepo.findAll();
		List<XEducationLevelModel> listEdu = this.eduRepo.findAll();

		// add object keluarga
		view.addObject("keluarga", keluarga);
		// add object list Relation
		view.addObject("listRel", listRel);
		// add object list Tree
		view.addObject("listTree", listTree);
		// add object list Edu
		view.addObject("listEdu", listEdu);
		return view;
	}

	@PostMapping(value = "/keluarga/save")
	public ModelAndView save(@Valid @ModelAttribute("keluarga") XKeluargaModel keluarga, BindingResult result) {
		// menampilkan view dari folder keluarga file _form.html
		ModelAndView view = new ModelAndView("keluarga/_form");

		if (result.hasErrors()) {
			logger.info("save biodata error");

			// add object keluarga dengan error nya ke view
			view.addObject("keluarga", keluarga);
		} else {
			// set user id
			keluarga.setCreatedBy(this.getAbuid());
			keluarga.setCreatedOn(new Date());
			// simpan ke repo
			sertRepo.save(keluarga);
			// add object baru tanpa error
			view.addObject("keluarga", new XKeluargaModel());
		}
		return view;
	}

	@GetMapping(value = "/keluarga/list/{bid}")
	private ModelAndView list(@PathVariable("bid") Long biodataId) {
		// view sertifkasi
		ModelAndView view = new ModelAndView("keluarga/_list");
		// get biodata Id
		XBiodataModel biodata = this.bioRepo.findById(biodataId).orElse(null);
		view.addObject("biodata", biodata);

		// get keluarga
		List<XKeluargaModel> listkeluarga = this.sertRepo.findByBiodataId(biodataId);
		view.addObject("listkeluarga", listkeluarga);
		return view;
	}

	@GetMapping(value = "/keluarga/hapus/{sid}")
	private ModelAndView hapus(@PathVariable("sid") Long sid) {
		// view sertifkasi
		ModelAndView view = new ModelAndView("keluarga/_hapus");
		// get keluarga
		XKeluargaModel keluarga = this.sertRepo.findById(sid).orElse(null);
		view.addObject("keluarga", keluarga);
		return view;
	}

	@PostMapping(value = "/keluarga/remove")
	private ModelAndView remove(@ModelAttribute("keluarga") XKeluargaModel keluarga) {
		// get keluarga
		XKeluargaModel item = this.sertRepo.findById(keluarga.getId()).orElse(null);

		// set delete
		item.setDeletedOn(new Date());
		item.setDeletedBy(this.getAbuid());
		item.setIsDelete(1);
		this.sertRepo.save(item);

		// view sertifkasi
		ModelAndView view = new ModelAndView("keluarga/_hapus");
		view.addObject("keluarga", item);
		return view;
	}

	@GetMapping(value = "/keluarga/ubah/{sid}") // bid sebagai vaiable biodataId
	public ModelAndView edit(@PathVariable("sid") Long sid) {
		// menampilkan view dari folder keluarga file _form.html
		ModelAndView view = new ModelAndView("keluarga/_form");
		// membuat object keluarga model
		XKeluargaModel keluarga = this.sertRepo.findById(sid).orElse(null);

		List<XFamilyRelationModel> listRel = this.relRepo.findAll();
		List<XFamilyTreeTypeModel> listTree = this.treeRepo.findAll();
		List<XEducationLevelModel> listEdu = this.eduRepo.findAll();

		// add object keluarga
		view.addObject("keluarga", keluarga);
		// add object list Relation
		view.addObject("listRel", listRel);
		// add object list Tree
		view.addObject("listTree", listTree);
		// add object list Edu
		view.addObject("listEdu", listEdu);
		return view;
	}
}
