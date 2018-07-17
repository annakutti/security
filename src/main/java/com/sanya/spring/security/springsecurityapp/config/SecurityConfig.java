/**
 * 
 */
package com.sanya.spring.security.springsecurityapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

/**
 * @author Sanya_s
 *
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		UserBuilder userBuilder = User.withDefaultPasswordEncoder();
		UserDetails userDetail = userBuilder.username("christy").password("christy").roles("USER").build();
		System.out.println("User Details >"+userDetail.getPassword());
		
		UserDetails adminDetail = userBuilder.username("admin").password("admin").roles("ADMIN").build();
		System.out.println("ADMIN Details >"+adminDetail.getPassword());
		
		auth.inMemoryAuthentication().passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
		.withUser(userDetail).withUser(adminDetail);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.authorizeRequests()
		.antMatchers("/rest/admin/**").hasRole("ADMIN")
		//.antMatchers("/rest/user/**").hasRole("USER").anyRequest().fullyAuthenticated().and().httpBasic();
		.anyRequest().fullyAuthenticated().and().httpBasic();
		httpSecurity.csrf().disable();
	}
}
