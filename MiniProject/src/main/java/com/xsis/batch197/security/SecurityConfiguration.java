package com.xsis.batch197.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserPrincipalDetailsService userService;
	
	@Autowired
	private AuthenticationSuccessHandler authSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authFailureHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		//url jika access benar
		.antMatchers("/").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/register").permitAll()
		//permitAll agar semua orang bisa akses
		.antMatchers("/index").hasAnyAuthority("ROLE_ADMINISTRATOR", "ROLE_BOOTCAMP_QC", "ROLE_INTERNAL_SYSDEV")
		.anyRequest().authenticated()
		.and()
		// disable csrf
		.csrf().disable()
		// form login
		.formLogin()
		.loginPage("/login")
		.failureUrl("/login?error=true")
		// jika sudah berhasil login akan diarahkan kemana
		.defaultSuccessUrl("/pelamar/index")
		// name text input di form login
		.usernameParameter("email")
		// name text input di form login
		.passwordParameter("password")
		.successHandler(authSuccessHandler)
		.failureHandler(authFailureHandler)
		.and()
		//logout
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
		.and()
		.exceptionHandling()
		.accessDeniedPage("/access-denied");
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(encoderPassword());
		daoAuthenticationProvider.setUserDetailsService(this.userService);

		return  daoAuthenticationProvider;
}
	@Bean
	PasswordEncoder encoderPassword() {
		return new BCryptPasswordEncoder();
}

}
