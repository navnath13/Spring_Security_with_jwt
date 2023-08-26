package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfiger {
	
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthentifcationFilter jwtAuthentifcationFilter;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		
		
		//configuration
		
		http.csrf(csrf-> csrf.disable())
		.cors(cors-> cors.disable())
		.authorizeHttpRequests(auth-> auth
			
				.requestMatchers("/auth/login").permitAll()
				.requestMatchers("/user/create").permitAll()
				.anyRequest()
				.authenticated()).exceptionHandling(ex-> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
		.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(jwtAuthentifcationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
		
		
	}
	
	public DaoAuthenticationProvider doAuthenticationProvider() {
		DaoAuthenticationProvider Provider=new DaoAuthenticationProvider();
		Provider.setUserDetailsService(userDetailsService);
		Provider.setPasswordEncoder(passwordEncoder);
	return Provider;

	}

}
