package com.xsis.batch197.model;

import com.xsis.batch197.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userRepo.deleteAll();

        UserModel roni = new UserModel("roni", passwordEncoder.encode("roni123"),"USER","");
        UserModel admin = new UserModel("admin", passwordEncoder.encode("admin123"),"ADMIN","ACCESS_TEST1,ACCESS_TEST1");
        UserModel manager = new UserModel("manager", passwordEncoder.encode("manager123"),"MANAGER","ACCESS_TEST1");

        List<UserModel> users = Arrays.asList(roni,admin,manager);
        this.userRepo.saveAll(users);
    }
}
