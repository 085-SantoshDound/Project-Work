package com.app.service;


import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;

import com.app.dto.AuthRequest;
import com.app.dto.UserDetailsDTO;
import com.app.entities.UserDetailsData;

public interface IUserDetails {
	UserDetailsDTO  addUserDetails(UserDetailsDTO user);
	UserDetailsData authonticateUser( AuthRequest logData);
	UserDetailsData	updateUserDetails( UserDetailsDTO updateDetails,Long id);
	String deleteUser(Long id);
}
