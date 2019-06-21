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

		private UserPrincipalDetailsService userService;

		public SecurityConfiguration(UserPrincipalDetailsService userService){
			this.userService = userService;
	}
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
//	@Autowired
//	private DataSource dataSource;
//	
//	@Autowired
//	  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//	    auth.jdbcAuthentication().dataSource(dataSource)
//	        .usersByUsernameQuery("select u.email, u.abpwd, 1 as enabled from x_addrbook u where u.email=?")
//	        .authoritiesByUsernameQuery("select u.email,  r.name from x_addrbook u inner join x_userrole ur on(u.id=ur.addrbook_id) inner join x_role r on(ur.role_id=r.id) where u.email=?");
//	  }
	
	
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
		// form login
		.csrf().disable().formLogin()
		.loginPage("/login")
		.failureUrl("/login?error=true")
		.defaultSuccessUrl("/pelamar/index")
		.usernameParameter("email")
		.passwordParameter("abpwd")
		.and()
		//logout
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login").and()
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
