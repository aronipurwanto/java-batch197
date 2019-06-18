package com.xsis.batch197.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private UserPrincipalDetailsService userService;

	public SecurityConfiguration(UserPrincipalDetailsService userService){
		this.userService = userService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		/*	inMemoryAuthentication()
			.withUser("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN")
			.and()
			.withUser("roni").password(passwordEncoder().encode("roni123")).roles("USER")
			.and()
			.withUser("manager").password(passwordEncoder().encode("manager123")).roles("MANAGER");
			*/
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/index.html").permitAll()
		.antMatchers("/biodata/*").authenticated()
		.antMatchers("/lookup/*").hasRole("ADMIN")
		.antMatchers("/kota/*").hasAnyRole("ADMIN","MANAGER","USER")
		.and()
		.formLogin()
		.loginProcessingUrl("/signin")
		.usernameParameter("txtUsernmae")
		.passwordParameter("txtPassword")
		.loginPage("/login").permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
		.and()
		.rememberMe()
		.rememberMeParameter("checkRememberMe")
		.tokenValiditySeconds(2592000).key("RahasiaDong!!");
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userService);

		return  daoAuthenticationProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
