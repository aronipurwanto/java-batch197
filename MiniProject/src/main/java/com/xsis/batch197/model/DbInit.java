package com.xsis.batch197.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.xsis.batch197.repository.XAddressBookRepo;
import com.xsis.batch197.repository.XCompanyRepo;
import com.xsis.batch197.repository.XEducationLevelRepo;
import com.xsis.batch197.repository.XFamilyRelationRepo;
import com.xsis.batch197.repository.XFamilyTreeTypeRepo;
import com.xsis.batch197.repository.XIdentityTypeRepo;
import com.xsis.batch197.repository.XMaritalStatusRepo;
import com.xsis.batch197.repository.XMenuRepo;
import com.xsis.batch197.repository.XNoteTypeRepo;
import com.xsis.batch197.repository.XReligionRepo;
import com.xsis.batch197.repository.XRoleRepo;
import com.xsis.batch197.repository.XSkillLevelRepo;
import com.xsis.batch197.repository.XTimePeriodeRepo;
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
	
	@Autowired
	private XCompanyRepo comRepo;
	
	@Autowired
	private XIdentityTypeRepo identityRepo;
	
	@Autowired
	private XTimePeriodeRepo timeRepo;
	
	@Autowired
	private XEducationLevelRepo eduRepo;
	
	@Autowired
	private XSkillLevelRepo skillRepo;
	
	@Autowired
	private XNoteTypeRepo noteTypeRepo;
	
	@Autowired
	private XFamilyTreeTypeRepo familyTreeRepo;
	
	@Autowired
	private XFamilyRelationRepo familyRelRepo;

	@Override
	public void run(String... args) throws Exception {
		// initial user
		if (this.userRepo.findAll().size() == 0) {
			XAddressBookModel admin = new XAddressBookModel("admin@gmail.com", "admin",
					encoderPassword.encode("admin123"));
			XAddressBookModel qc = new XAddressBookModel("quality@gmail.com", "quality",
					encoderPassword.encode("quality123"));
			XAddressBookModel sys = new XAddressBookModel("sysdev@gmail.com", "sysdev",
					encoderPassword.encode("sysdev123"));
			XAddressBookModel user = new XAddressBookModel("user@gmail.com", "user", encoderPassword.encode("user123"));
			List<XAddressBookModel> users = Arrays.asList(admin, qc, sys, user);

			this.userRepo.saveAll(users);
		}
		
		Long userId = this.userRepo.findByAbuid("admin").getId();
		// initial menu
		if (this.menuRepo.findAll().size() == 0) {
			List<XMenuModel> listMenu = new ArrayList<XMenuModel>();
			listMenu.add(new XMenuModel("Beranda", "fa fa-home", 0, 0, "home/index", "SIDEBAR", null, userId));
			listMenu.add(new XMenuModel("Pelamar", "fa fa-user", 1, 0, "pelamar/index", "SIDEBAR", null, userId));
			listMenu.add(new XMenuModel("Proses Pelamar", "fa fa-edit", 2, 0, "pelamar/proses", "SIDEBAR", null, userId));
			listMenu.add(new XMenuModel("Penjadwalan", "fa fa-calendar", 3, 0, "penjadwalan/index", "SIDEBAR", null, userId));
			listMenu.add(new XMenuModel("Tes", "fa fa-text-height", 4, 0, "test/index", "SIDEBAR", null, userId));
			listMenu.add(new XMenuModel("Bootcamp", "fa fa-laptop", 5, 0, "bootcamp/index", "SIDEBAR", null, userId));
			this.menuRepo.saveAll(listMenu);

			Long pelamar = this.menuRepo.findByTitle("Pelamar").getId();
			listMenu = new ArrayList<XMenuModel>();
			listMenu.add(new XMenuModel("Profile", "", 0, 1, "pelamar/profile", "BIODATA", pelamar, userId));
			listMenu.add(new XMenuModel("Biodata", "", 1, 1, "pelamar/biodata", "BIODATA", pelamar, userId));
			listMenu.add(
					new XMenuModel("Pelangalaman Kerja", "", 2, 1, "pelamar/pengalaman", "BIODATA", pelamar, userId));
			listMenu.add(new XMenuModel("Pendidikan", "", 3, 1, "pelamar/pendidikan", "BIODATA", pelamar, userId));
			listMenu.add(new XMenuModel("Pelatihan", "", 4, 1, "pelamar/pelatihan", "BIODATA", pelamar, userId));
			listMenu.add(new XMenuModel("Sertifikasi", "", 5, 1, "pelamar/sertifikasi", "BIODATA", pelamar, userId));
			listMenu.add(new XMenuModel("Sumber Lowongan Kerja", "", 6, 1, "pelamar/sumber-low-kerja", "BIODATA",
					pelamar, userId));
			listMenu.add(new XMenuModel("Organisasi", "", 7, 1, "pelamar/organisasi", "BIODATA", pelamar, userId));
			listMenu.add(new XMenuModel("Keluarga", "", 8, 1, "pelamar/keluarga", "BIODATA", pelamar, userId));
			listMenu.add(new XMenuModel("Keahlian", "", 9, 1, "pelamar/keahlian", "BIODATA", pelamar, userId));
			listMenu.add(new XMenuModel("Lain-Lain", "", 10, 1, "pelamar/biodata", "BIODATA", pelamar, userId));
			listMenu.add(new XMenuModel("Dokument", "", 11, 1, "pelamar/dokumen", "BIODATA", pelamar, userId));
			listMenu.add(new XMenuModel("Catatan", "", 12, 1, "pelamar/catatan", "BIODATA", pelamar, userId));
			listMenu.add(new XMenuModel("Aktifiasi Akun", "", 13, 1, "pelamar/aktifasi", "BIODATA", pelamar, userId));
			listMenu.add(new XMenuModel("Lihat Test", "", 14, 1, "pelamar/lihat-test", "BIODATA", pelamar, userId));
			listMenu.add(new XMenuModel("Hasil Test", "", 15, 1, "pelamar/hasil-test", "BIODATA", pelamar, userId));

			this.menuRepo.saveAll(listMenu);
		}
		// initial marital status
		if (this.maritalRepo.findAll().size() == 0) {
			List<XMaritalStatusModel> listMarital = new ArrayList<XMaritalStatusModel>();
			listMarital.add(new XMaritalStatusModel("SINGLE", "Single", userId));
			listMarital.add(new XMaritalStatusModel("MARRIED", "Married", userId));

			this.maritalRepo.saveAll(listMarital);
		}
		// initial religion
		if (this.religionRepo.findAll().size() == 0) {
			List<XReligionModel> listReligion = new ArrayList<XReligionModel>();

			listReligion.add(new XReligionModel("ISLAM", "Islam", userId));
			listReligion.add(new XReligionModel("KATHOLIK", "Katholik", userId));
			listReligion.add(new XReligionModel("KRISTEN", "Kristen", userId));
			listReligion.add(new XReligionModel("HINDU", "Hindu", userId));
			listReligion.add(new XReligionModel("BUDHA", "Budha", userId));
			listReligion.add(new XReligionModel("KEPERCAYAAN", "Kepercayaan", userId));
			listReligion.add(new XReligionModel("LAINNYA", "Lainnya", userId));

			this.religionRepo.saveAll(listReligion);
		}
		// initial repo
		if (this.roleRepo.findAll().size() == 0) {
			XRoleModel admin = new XRoleModel("ROLE_ADMINISTRATOR", "Role Administrator", userId);
			XRoleModel qc = new XRoleModel("ROLE_BOOTCAMP_QC", "Role Bootcamp QC", userId);
			XRoleModel sys = new XRoleModel("ROLE_INTERNAL_SYSDEV", "Role Internal Sysdev", userId);

			List<XRoleModel> listRole = Arrays.asList(admin, qc, sys);

			this.roleRepo.saveAll(listRole);
		}

		if (this.userRoleRepo.findAll().size() == 0) {
			List<XUserRoleModel> listUserRole = new ArrayList<XUserRoleModel>();

			Long userId1 = this.userRepo.findByAbuid("admin").getId();
			Long roleId1 = this.roleRepo.findByCode("ROLE_ADMINISTRATOR").getId();
			listUserRole.add(new XUserRoleModel(userId1, roleId1, userId));

			Long userId2 = this.userRepo.findByAbuid("quality").getId();
			Long roleId2 = this.roleRepo.findByCode("ROLE_BOOTCAMP_QC").getId();
			listUserRole.add(new XUserRoleModel(userId2, roleId2, userId));

			Long userId3 = this.userRepo.findByAbuid("sysdev").getId();
			Long roleId3 = this.roleRepo.findByCode("ROLE_INTERNAL_SYSDEV").getId();
			listUserRole.add(new XUserRoleModel(userId3, roleId3, userId));

			this.userRoleRepo.saveAll(listUserRole);
		}
		
		// initial company
		if(this.comRepo.findAll().size()==0) {
			List<XCompanyModel> listCompany = new ArrayList<>();
			
			listCompany.add(new XCompanyModel("XMU","Xsis Mitra Utama", userId));
			listCompany.add(new XCompanyModel("XA","Xsis Academy", userId));
			listCompany.add(new XCompanyModel("ETG","Equine Technology Group", userId));
			listCompany.add(new XCompanyModel("ODI","Optima Data International", userId));
			listCompany.add(new XCompanyModel("NPP","Niaga Prima Paramitra", userId));
			
			this.comRepo.saveAll(listCompany);
		}
		
		// initial identity
		if(this.identityRepo.findAll().size()==0) {
			List<XIdentityTypeModel> identityList = new ArrayList<XIdentityTypeModel>();
			identityList.add(new XIdentityTypeModel("KTP", "Kartu Tanda Penduduk", userId));
			identityList.add(new XIdentityTypeModel("SIM", "Surat Ijin Mengemudi", userId));
			identityList.add(new XIdentityTypeModel("PASSPOR", "Paspor", userId));
			
			this.identityRepo.saveAll(identityList);
		}
		
		if(this.timeRepo.findAll().size()==0) {
			List<XTimePeriodeModel> listTime = new ArrayList<XTimePeriodeModel>();
			listTime.add(new XTimePeriodeModel("HARI", "Hari", userId));
			listTime.add(new XTimePeriodeModel("MINGGU", "Minggu", userId));
			listTime.add(new XTimePeriodeModel("BULAN", "Bulan", userId));
			listTime.add(new XTimePeriodeModel("TAHUN", "Tahun", userId));
			
			this.timeRepo.saveAll(listTime);
		}
		
		if(this.eduRepo.findAll().size()==0) {
			List<XEducationLevelModel> listEdu = new ArrayList<XEducationLevelModel>();
			listEdu.add(new XEducationLevelModel("SD","Sekolah Dasar", userId));
			listEdu.add(new XEducationLevelModel("SMP","Sekolah Menengah Pertama", userId));
			listEdu.add(new XEducationLevelModel("SMA","Sekolah Menengah Atas", userId));
			listEdu.add(new XEducationLevelModel("D1","Diploma I", userId));
			listEdu.add(new XEducationLevelModel("D2","Diploma II", userId));
			listEdu.add(new XEducationLevelModel("D3","Diploma III", userId));
			listEdu.add(new XEducationLevelModel("D4","Diploma IV", userId));
			listEdu.add(new XEducationLevelModel("S1","Sarjana", userId));
			listEdu.add(new XEducationLevelModel("S2","Magister", userId));
			listEdu.add(new XEducationLevelModel("S3","Doktoral", userId));
			
			this.eduRepo.saveAll(listEdu);
		}
		
		if(this.skillRepo.findAll().size()==0) {
			List<XSkillLevelModel> listSkill = new ArrayList<XSkillLevelModel>();
			listSkill.add(new XSkillLevelModel("Junior","Junior",userId));
			listSkill.add(new XSkillLevelModel("Middle","Middle",userId));
			listSkill.add(new XSkillLevelModel("Senior","Senior",userId));
			
			this.skillRepo.saveAll(listSkill);
		}
		
		if(this.noteTypeRepo.findAll().size()==0) {
			List<XNoteTypeModel> listType = new ArrayList<XNoteTypeModel>();
			listType.add(new XNoteTypeModel("TRO","Tenichal Reviewer Officer", userId));
			listType.add(new XNoteTypeModel("SRO","Soft Reviewer Officer", userId));
			listType.add(new XNoteTypeModel("HRO","Hard Reviewer Officer", userId));
			listType.add(new XNoteTypeModel("MRO","Manual Reviewer Officer", userId));
			
			this.noteTypeRepo.saveAll(listType);
		}
		
		if(this.familyTreeRepo.findAll().size()==0) {
			List<XFamilyTreeTypeModel> listTree = new ArrayList<XFamilyTreeTypeModel>();
			listTree.add(new XFamilyTreeTypeModel("AYAH", "Ayah",userId));
			listTree.add(new XFamilyTreeTypeModel("IBU", "Ibu",userId));
			listTree.add(new XFamilyTreeTypeModel("ANAK", "Anak",userId));
			
			this.familyTreeRepo.saveAll(listTree);
		}
		
		if(this.familyRelRepo.findAll().size()==0) {
			List<XFamilyRelationModel> listRel = new ArrayList<XFamilyRelationModel>();
			listRel.add(new XFamilyRelationModel("SAUDARA","Saudara Kandung"));
			listRel.add(new XFamilyRelationModel("ORTU","Orang Tua"));
			listRel.add(new XFamilyRelationModel("KAKEK","Kakek"));
			listRel.add(new XFamilyRelationModel("NENEK","Nenek"));
			
			this.familyRelRepo.saveAll(listRel);
		}
	}

}
