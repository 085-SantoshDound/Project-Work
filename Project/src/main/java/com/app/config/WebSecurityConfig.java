package com.app.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.filter.JWTRequestFilter;


@EnableWebSecurity 
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired 
	private UserDetailsService userDetailsService;
	
	
	@Autowired
	private JWTRequestFilter filter;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
		System.out.println("In config to check with req : "+userDetailsService.toString());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable().
		exceptionHandling()
		.authenticationEntryPoint((request, response, ex) -> {
            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    ex.getMessage()
                );
            }).
		and()
		
		.authorizeRequests()
		//.antMatchers("/user","/**").hasAnyRole("USER","ADMIN")
		//.antMatchers("/admin","/api/product/**").hasRole("ADMIN")
		//.antMatchers("/api/product/**").permitAll() //hasAnyRole("ADMIN","USER")
		.antMatchers("/api/product/get/**").hasAnyRole("ADMIN","USER")
		//.antMatchers("/api/user/update/**").hasRole("ADMIN")
		//.antMatchers("/api/validate/","/**").hasAnyRole("USER")
		//.antMatchers("/api/validate/","/**").hasRole("USER")
		//.antMatchers("/api/validate/","/**").hasRole("ADMIN")
		
		
		
		.antMatchers("/api/validate/**").permitAll()
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		.anyRequest().authenticated()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
	//configure auth mgr bean : to be used in Auth REST controller

	@Bean
	public AuthenticationManager authenticationMgr() throws Exception{
		return super.authenticationManagerBean();
	}
	
}
