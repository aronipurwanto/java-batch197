package com.xsis.batch197.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xsis.batch197.model.UserModel;
import com.xsis.batch197.repository.UserRepo;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
	private UserRepo userRepo;
	
	public UserPrincipalDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = this.userRepo.findByUserAndEmail(username);
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}

}
