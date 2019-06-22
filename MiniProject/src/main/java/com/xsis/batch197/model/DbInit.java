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
import com.xsis.batch197.repository.XMenuRepo;
import com.xsis.batch197.repository.XReligionRepo;
import com.xsis.batch197.repository.XRoleRepo;
import com.xsis.batch197.repository.XUserRoleRepo;

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
	
	@Autowired
	private XMenuRepo menuRepo;
	
	@Autowired
	private XUserRoleRepo userRoleRepo;

	/*
	 * public DbInit(XAddressBookRepo userRepo, PasswordEncoder encoderPassword) {
	 * this.userRepo = userRepo; this.encoderPassword = encoderPassword; }
	 */

	@Override
	public void run(String... args) throws Exception {
		// initial menu
		if(this.menuRepo.findAll().size()==0) {
			List<XMenuModel> listMenu = new ArrayList<XMenuModel>();
			listMenu.add(new XMenuModel("Beranda","",0,0,"/home/index","SIDEBAR"));
			listMenu.add(new XMenuModel("Pelamar","",1,0,"/pelamar/index","SIDEBAR"));
			listMenu.add(new XMenuModel("Proses Pelamar","",2,0,"/pelamar/proses","SIDEBAR"));
			listMenu.add(new XMenuModel("Penjadwalan","",3,0,"/penjadwalan/index","SIDEBAR"));
			listMenu.add(new XMenuModel("Tes","",4,0,"/test/index","SIDEBAR"));
			listMenu.add(new XMenuModel("Bootcamp","",5,0,"/bootcamp/index","SIDEBAR"));
			this.menuRepo.saveAll(listMenu);
			
			Long pelamar = this.menuRepo.findByTitle("Pelamar").getId();
			listMenu = new ArrayList<XMenuModel>();
			listMenu.add(new XMenuModel("Profile","",0,1,"/pelamar/profile","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Biodata","",1,1,"/pelamar/biodata","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Pelangalaman Kerja","",2,1,"/pelamar/pengalaman","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Pendidikan","",3,1,"/pelamar/pendidikan","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Pelatihan","",4,1,"/pelamar/pelatihan","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Sertifikasi","",5,1,"/pelamar/sertifikasi","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Sumber Lowongan Kerja","",6,1,"/pelamar/sumber-low-kerja","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Organisasi","",7,1,"/pelamar/organisasi","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Keluarga","",8,1,"/pelamar/keluarga","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Keahlian","",9,1,"/pelamar/keahlian","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Lain-Lain","",10,1,"/pelamar/biodata","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Dokument","",11,1,"/pelamar/dokumen","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Catatan","",12,1,"/pelamar/catatan","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Aktifiasi Akun","",13,1,"/pelamar/aktifasi","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Lihat Test","",14,1,"/pelamar/lihat-test","BIODATA", pelamar));
			listMenu.add(new XMenuModel("Hasil Test","",15,1,"/pelamar/hasil-test","BIODATA", pelamar));
			
			this.menuRepo.saveAll(listMenu);
		}
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
		
		if(this.userRoleRepo.findAll().size()==0) {
			List<XUserRoleModel> listUserRole =new ArrayList<XUserRoleModel>();
			Long userId1 = this.userRepo.findByAbuid("admin").getId();
			Long roleId1 = this.roleRepo.findByCode("ROLE_ADMINISTRATOR").getId();
			listUserRole.add(new XUserRoleModel(userId1, roleId1));
			
			Long userId2 = this.userRepo.findByAbuid("quality").getId();
			Long roleId2 = this.roleRepo.findByCode("ROLE_BOOTCAMP_QC").getId();
			listUserRole.add(new XUserRoleModel(userId2, roleId2));
			
			Long userId3 = this.userRepo.findByAbuid("sysdev").getId();
			Long roleId3 = this.roleRepo.findByCode("ROLE_INTERNAL_SYSDEV").getId();
			listUserRole.add(new XUserRoleModel(userId3, roleId3));
			
			this.userRoleRepo.saveAll(listUserRole);
		}
	}

}
