package com.xsis.batch197.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.batch197.model.XAddressBookModel;
import com.xsis.batch197.model.XRoleModel;
import com.xsis.batch197.modelview.ChangePassword;
import com.xsis.batch197.modelview.ForgotPassword;
import com.xsis.batch197.modelview.FormLogin;
import com.xsis.batch197.repository.XAddressBookRepo;
import com.xsis.batch197.repository.XCompanyRepo;
import com.xsis.batch197.repository.XRoleRepo;
import com.xsis.batch197.security.NotificationService;

@Controller
public class AuthenticationController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private XRoleRepo roleRepo;
	
	@Autowired
	NotificationService notifService;

	@Autowired
	private XCompanyRepo comRepo;

	@Autowired
	private XAddressBookRepo addrBookRepo;

	@GetMapping(value = "/login")
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
		ModelAndView view = new ModelAndView("auth/forgot-password");
		view.addObject("forgot", forgot);
		return view;
	}

	@PostMapping(value = "/check-email")
	public ModelAndView checkEmail(@Valid @ModelAttribute("forgot") ForgotPassword forgot, BindingResult result) {
		ModelAndView view = new ModelAndView("auth/forgot-password");
		if (result.hasErrors()) {
			view.addObject("forgot", forgot);
		} else {
			// cari dulu user berdasarkan email
			XAddressBookModel user = this.addrBookRepo.findByEmail(forgot.getEmail());
			// jika di check user berdasarkan email tidak ada, maka tampilkan pesan berikut
			if (user == null) {
				result.addError(new FieldError("forgot", "email", forgot.getEmail(), false, null, null,
						"Email anda tidak terdaftar."));
				view.addObject("forgot", forgot);
			} else if (user.getIsLocked() == 1) {
				// jika user di lock
				result.addError(new FieldError("forgot", "email", forgot.getEmail(), false, null, null,
						"User anda terkunci, Tidak bisa merubah"));
				view.addObject("forgot", forgot);

			} else if (user.getIsDelete() == 1) {
				// jika user anda tidak aktif
				result.addError(new FieldError("forgot", "email", forgot.getEmail(), false, null, null,
						"User anda tidak aktif, Tidak bisa merubah"));
				view.addObject("forgot", forgot);
			} else {
				// kirim link email
				try {
					this.notifService.sendMailFotgotPassword(forgot.getEmail());
				} catch (MailException e) {
					logger.info(e.getMessage());
				}
			}
		}
		return view;
	}
	
	

	// setelah login pilih role dan pilih company
	@GetMapping(value = "/select-role")
	public ModelAndView selectAccess() {
		ModelAndView view = new ModelAndView("auth/select-access");
		// check auth
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<XRoleModel> listRole = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			// cari user berdasarkan username ke database
			XAddressBookModel user = this.addrBookRepo.findByAbuid(auth.getName());
			// list role nya
			listRole = user.getListRole();
		}
		// add object to list
		view.addObject("listRole", listRole);
		view.addObject("listCompany", this.comRepo.findAll());

		return view;
	}

	// untuk menangkap request setelah dipilih role dan company
	@PostMapping(value = "/set-access")
	public String setAccess(@RequestParam("roleId") Long roleId, @RequestParam("companyId") Long companyId,
			HttpSession session) {
		session.setAttribute("roleId", roleId);
		session.setAttribute("companyId", companyId);

		return "redirect:/home/index";
	}
	
	@GetMapping(value="reset-password/{token}")
	public ModelAndView changePassword(@PathVariable("token") String token) {
		ModelAndView view = new ModelAndView("aut/change-password");
		// lihat di package modelview
		ChangePassword reset = new ChangePassword();
		
		//add object ke view
		view.addObject("reset", reset);
		return view;
	}

	@PostMapping(value = "/login")
	public ModelAndView checkUser(@Valid @ModelAttribute("user") FormLogin user, BindingResult result) {
		ModelAndView view = new ModelAndView("aut/login");
		if (result.hasErrors()) {
			view.addObject("user", user);
		} else {
			
		}
		return view;
	}
}
