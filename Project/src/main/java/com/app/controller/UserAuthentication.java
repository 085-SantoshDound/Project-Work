package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ErrorResponse;
import com.app.dto.AuthRequest;
import com.app.dto.SaveNameDTO;
import com.app.dto.UserDetailsDTO;
import com.app.entities.UserRole;
import com.app.service.IUserDetails;

import lombok.extern.slf4j.Slf4j;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserAuthentication {

	@Autowired
	private IUserDetails details;
	public UserAuthentication() {
		// TODO Auto-generated constructor stub
		System.out.println("in users controller"+getClass());
		log.info("in ctor of "+getClass());
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> authonticateuser(@Valid @RequestBody AuthRequest logData, BindingResult br)
	{
		if(br.hasFieldErrors())
			return new ResponseEntity<>(new ErrorResponse( "Invalid login input"),HttpStatus.BAD_REQUEST);
		System.out.println("In LogIn (Authontication) Method : "+logData);
		//System.out.println("In Log in method User details from back end: "+uDetails);
		
		
		//authonticate(logData.getEmail(),logData.getPassword()));
		return new ResponseEntity<>(details.authonticateUser(logData),HttpStatus.ACCEPTED);// new ResponseEntity<>()
	}
	@PostMapping("/update/profile/{id}")
	public ResponseEntity<?> updateUserProfile(@Valid @RequestBody UserDetailsDTO updateDetails,BindingResult br,@PathVariable Long id)
	{
		System.out.println("Updata details of user :"+updateDetails);
		if(br.hasFieldErrors())
			return new ResponseEntity<>(new ErrorResponse("Update details not valid"),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(details.updateUserDetails(updateDetails,id),HttpStatus.CREATED);
	}
	@GetMapping("/delete/{id}") 
	public ResponseEntity<?> deleteUser(@Valid @PathVariable Long id)
	{
		System.out.println("In user Delete controller: " +id);
		if(id==null)
			new ResponseEntity<>(new ErrorResponse("Invalid id"),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(details.deleteUser(id),HttpStatus.OK);
		
	}

	
	@GetMapping("/home")
	public String getUser()
	{
		System.out.println("in users get home method");
		return "Home";
	}
	@PostMapping("/name")
	public ResponseEntity<?> saveName(@RequestBody @Valid SaveNameDTO name)
	{
		System.out.println("IN TEMP METHOD ");
		System.out.println("IN TEMP METHOD "+name);
		
		return new ResponseEntity<>(name,HttpStatus.CREATED);
	}

	
	
}
