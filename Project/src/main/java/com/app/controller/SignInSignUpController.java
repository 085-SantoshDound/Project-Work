package com.app.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.*;
import org.springframework.validation.BindingResult;

import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.ErrorResponse;
import com.app.dto.UserDetailsDTO;
import com.app.entities.UserDetailsData;
import com.app.entities.UserRole;
import com.app.service.EmailService;
import com.app.service.IUserDetails;
import com.app.utils.JwtUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/validate")
@Slf4j
public class SignInSignUpController {
	//dep : JWT utils : for generating JWT
		//@Autowired
		@Autowired
		private JwtUtils utils;
		// dep : Auth mgr
		@Autowired
		private AuthenticationManager manager;
		
		@Autowired
		private EmailService emailService;
		
		@Autowired
		private IUserDetails details;

		// add a method to authenticate user . Incase of success --send back token , o.w
		// send back err mesg
		@PostMapping("/signin")
		public ResponseEntity<?> validateUserCreateToken(@RequestBody @Valid AuthRequest request)
		{
			// store incoming user details(not yet validated) into Authentication object
			// Authentication i/f ---> imple by UserNamePasswordAuthToken
			System.out.println("in valdiate sign in method");
			log.info("in sign in info : reuest {} "+request); 
			UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(request.getEmail(),
					request.getPassword());
			log.info("auth token "+authToken);
			try {
				
			Authentication authenticateDetails=manager.authenticate(authToken);
			//=> auth success
			return ResponseEntity.ok(new AuthResp("Auth successful!", utils.generateJwtToken(authenticateDetails)));
			}catch (BadCredentialsException e) {
				//send back err res code
				System.out.println("error"+e);;
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}
		}
		@PostMapping("/register")
		public ResponseEntity<?> registerUser(@RequestBody @Valid  UserDetailsDTO user, BindingResult br) throws MessagingException
		{
			
			System.out.println("in  registerUser controller method");
			System.out.println("in registerUser controller method"+user);
				
				if(user.getUserRole()==null)
					user.setUserRole(UserRole.ROLE_USER);
				
			if(br.hasFieldErrors())
				return new ResponseEntity<>(new ErrorResponse( "Invalid input"),HttpStatus.BAD_REQUEST);
			
			UserDetailsDTO data=details.addUserDetails(user);
				if(data==null)
					throw new MessagingException();
				String str=data.getEmail();
				String pass=user.getPassword();
				emailService.sendSimpleEmail(str,"You have registered successfully!\nEmail = "+str+"\nPassword = "+pass ,"\nWelcome To DRONE GALAXY SERVICES !!");
				System.out.println("mail send succesffully with username :"+str);
				return ResponseEntity.status(HttpStatus.CREATED).body(data);
		}
}
