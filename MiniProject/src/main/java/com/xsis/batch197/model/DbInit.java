package com.xsis.batch197.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.xsis.batch197.repository.XAddressBookRepo;
import com.xsis.batch197.repository.XMaritalStatusRepo;
import com.xsis.batch197.repository.XReligionRepo;
import com.xsis.batch197.repository.XRoleRepo;

@Service
public class DbInit implements CommandLineRunner {
	@Autowired
	private XAddressBookRepo userRepo;

	@Autowired
	private PasswordEncoder encoderPassword;

	@Autowired
	private XReligionRepo religionRepo;

	@Autowired
	private XRoleRepo roleRepo;
	
	@Autowired
	private XMaritalStatusRepo maritalRepo;
	
	/*
	@Autowired
	private XFamilyTreeTypeModel familyTreeRepo;

	/*
	 * public DbInit(XAddressBookRepo userRepo, PasswordEncoder encoderPassword) {
	 * this.userRepo = userRepo; this.encoderPassword = encoderPassword; }
	 */

	@Override
	public void run(String... args) throws Exception {
		// initial marital status
		if(this.maritalRepo.findAll().size()==0) {
			List<XMaritalStatusModel> listMarital = new ArrayList<XMaritalStatusModel>();
			listMarital.add(new XMaritalStatusModel("SINGLE","Single"));
			listMarital.add(new XMaritalStatusModel("MARRIED","Married"));
			this.maritalRepo.saveAll(listMarital);
		}
		// initial religion
		if (this.religionRepo.findAll().size() == 0) {
			List<XReligionModel> listReligion = new ArrayList<XReligionModel>();
			listReligion.add(new XReligionModel("ISLAM","Islam"));
			listReligion.add(new XReligionModel("KATHOLIK","Katholik"));
			listReligion.add(new XReligionModel("KRISTEN","Kristen"));
			listReligion.add(new XReligionModel("HINDU","Hindu"));
			listReligion.add(new XReligionModel("BUDHA","Budha"));
			listReligion.add(new XReligionModel("KEPERCAYAAN","Kepercayaan"));
			listReligion.add(new XReligionModel("LAINNYA","Lainnya"));
			this.religionRepo.saveAll(listReligion);
		}
		// initial repo
		if (this.roleRepo.findAll().size() == 0) {
			XRoleModel admin = new XRoleModel("ROLE_ADMINISTRATOR", "Role Administrator");
			XRoleModel qc = new XRoleModel("ROLE_BOOTCAMP_QC", "Role Bootcamp QC");
			XRoleModel sys = new XRoleModel("ROLE_INTERNAL_SYSDEV", "Role Internal Sysdev");
			List<XRoleModel> listRole  = Arrays.asList(admin, qc, sys);
			this.roleRepo.saveAll(listRole);
		}

		if (this.userRepo.findAll().size() == 0) {
			XAddressBookModel admin = new XAddressBookModel("admin@gmail.com", "admin",encoderPassword.encode("admin123"));
			XAddressBookModel qc = new XAddressBookModel("quality@gmail.com", "quality",encoderPassword.encode("quality123"));
			XAddressBookModel sys = new XAddressBookModel("sysdev@gmail.com", "sysdev",encoderPassword.encode("sysdev123"));
			XAddressBookModel user = new XAddressBookModel("user@gmail.com", "user", encoderPassword.encode("user123"));
			List<XAddressBookModel> users = Arrays.asList(admin, qc, sys, user);
			this.userRepo.saveAll(users);
		}
	}

}
