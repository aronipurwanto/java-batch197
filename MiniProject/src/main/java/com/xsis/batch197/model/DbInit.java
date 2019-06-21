package com.xsis.batch197.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.xsis.batch197.repository.XAddressBookRepo;

@Service
public class DbInit implements CommandLineRunner {
	private XAddressBookRepo userRepo;

	private PasswordEncoder encoderPassword;
	
	public DbInit(XAddressBookRepo userRepo, PasswordEncoder encoderPassword){
        this.userRepo = userRepo;
        this.encoderPassword = encoderPassword;
    }

	@Override
	public void run(String... args) throws Exception {
		this.userRepo.deleteAll();

        XAddressBookModel irman = new XAddressBookModel("irman@gmail.com", "irman", encoderPassword.encode("irman123"));
        XAddressBookModel kris = new XAddressBookModel("kris@gmail.com", "kris", encoderPassword.encode("kris123"));
        List<XAddressBookModel> users = Arrays.asList(irman, kris);
        this.userRepo.saveAll(users);
}
		
	}
	


