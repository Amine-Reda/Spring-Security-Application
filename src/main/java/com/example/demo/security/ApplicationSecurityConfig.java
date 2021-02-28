package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final PasswordEncoder passwordencoder;

@Autowired
public ApplicationSecurityConfig(PasswordEncoder passwordencoder) {
	this.passwordencoder = passwordencoder;
}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		      .authorizeRequests().antMatchers("/","index","/css/*","/js/*").permitAll()
		     .antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
		     .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_write.name())
		     .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_write.name())
		     .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_write.name())
		     .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(),ApplicationUserRole.ADMINTRAINER.name())
		     .anyRequest()
		      .authenticated()
		      .and()
		      .httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		
	UserDetails amineRedaUser= User.builder()
	.username("AmineReda")
	.password(passwordencoder.encode("password"))
	//.roles(ApplicationUserRole.STUDENT.name())
	.authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
	.build();
	
	UserDetails hajarHalouaniUser =User.builder()
			.username("HajarHalouani")
			.password(passwordencoder.encode("password123"))
		//	.roles(ApplicationUserRole.ADMIN.name())
			.authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
			.build();
	
	UserDetails youssefMazgouriUser =User.builder()
			.username("YoussefMazgouri")
			.password(passwordencoder.encode("password123"))
		//	.roles(ApplicationUserRole.ADMINTRAINER.name())
			.authorities(ApplicationUserRole.ADMINTRAINER.getGrantedAuthorities())
			.build();
	
	return new InMemoryUserDetailsManager(amineRedaUser,hajarHalouaniUser,youssefMazgouriUser);
	}
	
	

	
}
