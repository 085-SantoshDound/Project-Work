package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.utils.JwtUtils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/cart")
//@CrossOrigin
@Slf4j
public class CartController {

	@Autowired
	private JwtUtils utils;
	
	//@Autowired
	//private AuthenticationManager authenticationMgr;
	//private Authentication authenticate;
	
	public CartController()
	{
		log.info("message");

		System.out.println("in cart controller ");
	}
	
	@GetMapping("/{userID}/product/{id}")
	public ResponseEntity<?> getCartDetailsForUser(@PathVariable Long id,@PathVariable Long userID)
	{
		
		System.out.println("in product get cart controller");
		
	//	System.out.println(authenticationMgr.authenticate(token));
		//String UserName=;//
		return null;
	}
}
