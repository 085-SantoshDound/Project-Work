package com.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.app.entities.UserDetailsData;

import lombok.NoArgsConstructor;
import lombok.ToString;


@Component
@ToString
@NoArgsConstructor
public class CustomeUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDetailsData data;
	//public Object getUserName;

	/*
	 //configure BCryptPassword encode bean
	@Bean
	public PasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	} 
	*/
	
	
	public CustomeUserDetails(UserDetailsData data)
	{
		super();
		this.data=data;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		// Meaning : This method should ret Collection(List) of granted authorities ,
		// for a specific user --which will be later stored in Auth obj
		//SimpleGrantedAuthority(String roleName)  imple  GrantedAuthority
		//UserEntity ---> Role	
		Collection< SimpleGrantedAuthority> authorities=new ArrayList<SimpleGrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority(data.getUserRole().name()));
		
				
		return authorities ;
		
		//return data.getUserRole().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
				//.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return data.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return data.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
