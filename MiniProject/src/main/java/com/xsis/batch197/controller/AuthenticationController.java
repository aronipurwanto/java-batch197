package com.xsis.batch197.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.batch197.model.XAddressBookModel;
import com.xsis.batch197.modelview.ForgotPassword;
import com.xsis.batch197.repository.XAddressBookRepo;
import com.xsis.batch197.repository.XCompanyRepo;
import com.xsis.batch197.repository.XRoleRepo;

@Controller
public class AuthenticationController {	
	@Autowired
	private XRoleRepo roleRepo;

	@Autowired
	private XCompanyRepo comRepo;
	
	@Autowired
	private XAddressBookRepo addrBookRepo;
	
	@GetMapping(value="/login")
	public String login() {
		return "auth/login";
	}
	
	@GetMapping(value = "/register")
	public String register() {
		return "auth/register";
	}
	
	@GetMapping(value = "/forgot-password")
	public ModelAndView forgot() {
		ForgotPassword forgot = new ForgotPassword();
		ModelAndView view =new ModelAndView("auth/forgot-password");
		view.addObject("forgot", forgot);
		return view;
	}
	
	@PostMapping(value="/check-email")
	public ModelAndView checkEmail(@Valid @ModelAttribute("forgot") ForgotPassword forgot, BindingResult result) {
		ModelAndView view = new ModelAndView("auth/forgot-password");
		if(result.hasErrors()) {
			view.addObject("forgot", forgot);
		}else {
			// cari dulu user berdasarkan email
			XAddressBookModel user = this.addrBookRepo.findByEmail(forgot.getEmail());
			// jika di check user berdasarkan email tidak ada, maka tampilkan pesan berikut
			if(user==null) {
				ObjectError error = new ObjectError("email","Email anda tidak terdaftar.");
				result.addError(error);
				view.addObject("forgot", forgot);
			}else if(user.getIsLocked()==1){
				// jika user di lock
				ObjectError error = new ObjectError("email","User anda terkunci, Tidak bisa merubah");
				result.addError(error);
				view.addObject("forgot", forgot);
				
			}else if(user.getIsDelete()==1) {
				// jika user anda tidak aktif
				ObjectError error = new ObjectError("email","User anda tidak aktif, Tidak bisa merubah");
				result.addError(error);
				view.addObject("forgot", forgot);
			}else {
				// kirim link email
			}
		}
		return view;
	}

	// setelah login pilih role dan pilih company
	@GetMapping(value = "/select-role")
	public ModelAndView selectAccess() {
		ModelAndView view = new ModelAndView("auth/select-access");
		// add object to list
		view.addObject("listRole", this.roleRepo.findAll());
		view.addObject("listCompany", this.comRepo.findAll());

		return view;
	}

	// untuk menangkap request setelah dipilih role dan company
	@PostMapping(value = "/set-access")
	private String setAccess(@RequestParam("roleId") Long roleId, @RequestParam("companyId") Long companyId, HttpSession session) {
		session.setAttribute("roleId", roleId);
		session.setAttribute("companyId", companyId);
		
		return "redirect:/home/index";
	}
}
