package com.xsis.batch197.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.xsis.batch197.model.UserModel;

public class UserPrincipal implements UserDetails {
	private UserModel user;
	
	public UserPrincipal(UserModel user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		this.user.getPermisstionList().forEach(p ->{
			GrantedAuthority authority = new SimpleGrantedAuthority(p);
			authorities.add(authority);
		});
		/*
		this.user.getRolesList().forEach(r ->{
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+r);
			authorities.add(authority);
		});
		*/
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.user.getIsExpired()==0;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.user.getIsLocked()==0;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.user.getActive()==1;
	}

}
