package com.authapi.authconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.authapi.service.UserDetailsModel;
import com.authapi.userrepository.UserRepository;
import com.authapi.util.AuthenticationFilter;

@Configuration
//@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationFilter filter;

	// Authentication
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		auth.inMemoryAuthentication().withUser("vivek").password(getPasswordEncoder().encode("pass1234")).roles("USER")
//				.and().withUser("amal").password(getPasswordEncoder().encode("amal1234")).roles("ADMIN");
////		.and().passwordEncoder(getPasswordEncoder());
//	}

	// authorization
	// Role based authorization API level
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http/* .cors().disable() */.csrf().disable().authorizeHttpRequests().antMatchers("/admin/**")
				.hasAnyRole("ADMIN").antMatchers("/user/**").hasAnyRole("USER").antMatchers("/**").permitAll()
				.anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

//				.antMatchers(HttpMethod.POST,"/**")
//				.permitAll()
//				.antMatchers("/**").permitAll();
//				.and().formLogin()
//				.and().httpBasic();
	}

	// Authentication
	// Creating users from database

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.userDetailsService(userDetailsService);
//
//	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.userDetailsService(username->new UserDetailsModel(repo.findByUsername(username)));
//		
//	}

//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}

	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManager();
	}


//	@Bean
//	PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}
