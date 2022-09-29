package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.DroneTypeDTO;
import com.app.dto.AuthRequest;
import com.app.entities.UserDetailsData;
import com.app.repository.AdminRepository;


@Service
@Transactional
public class AdminServiceImp implements IAdminService {
	@Autowired
	private AdminRepository aRepo;
	
	@Override
	public UserDetailsData adminAuthontication(AuthRequest login) {
		System.out.println("log in data in service method of authoniticate user : "+login);
		UserDetailsData uData=aRepo.findByEmail(login.getEmail());
		System.out.println("User details in authon : "+uData);
		
		if(uData==null)
			throw new ResourceNotFoundException("User Not Found In Authonticattion server "+getClass());
		boolean match=uData.getPassword().equals(login.getPassword()) && uData.getUserRole().equals("ADMIN");
		if(!match)
			throw new ResourceNotFoundException("Password not match in authontication servivce method");
		return uData;
	}

	
}

