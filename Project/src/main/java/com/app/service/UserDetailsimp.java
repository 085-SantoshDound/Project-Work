package com.app.service;

import javax.transaction.Transactional;

import org.apache.tomcat.jni.Address;
import org.hibernate.loader.plan.exec.process.internal.AbstractRowReader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.AuthRequest;
import com.app.dto.UserDetailsDTO;
import com.app.entities.UserAddress;
import com.app.entities.UserDetailsData;
import com.app.repository.UserAddressRepository;
import com.app.repository.UsersRepository;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;

@Service
@Transactional
public class UserDetailsimp implements IUserDetails, UserDetailsService {
	@Autowired
	private UsersRepository uRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserAddressRepository adRepo;
	@Autowired PasswordEncoder bcrypEncoder;
	
	@Override
	public UserDetailsDTO addUserDetails(UserDetailsDTO user) {
		
		System.out.println("in service layer "+user);
		UserDetailsData details=new UserDetailsData();
		BeanUtils.copyProperties(user,details);
		details.setPassword(bcrypEncoder.encode(user.getPassword()));
		
		System.out.println("user details in service"+details);
		UserAddress useraddress=new UserAddress();
		
		BeanUtils.copyProperties(user, useraddress);
		System.out.println("user details in service"+useraddress);
		//UserAddress addressAdded=adRepo.save(useraddress);
		details.setUserAddres(useraddress);
		
		UserDetailsData userAdded=uRepo.save(details);
		
		
		UserDetailsDTO userDato=mapper.map(userAdded, UserDetailsDTO.class);
						BeanUtils.copyProperties(useraddress, userDato);
		
		System.out.println("user details in service : "+userDato );
		
		return userDato;
		
	}
	@Override
	public UserDetailsData authonticateUser(AuthRequest logData) {
		// TODO Auto-generated method stub
		System.out.println("log in data in service method of authoniticate user : "+logData);
		UserDetailsData uData=uRepo.findByEmail(logData.getEmail());
		System.out.println("User details in authon : "+uData);
		
		if(uData==null)
			throw new ResourceNotFoundException("User Not Found In Authonticattion server "+getClass());
		boolean match=bcrypEncoder.matches(logData.getEmail(), logData.getPassword());
		System.out.println("matches : "+match);
		if(!match)
			throw new ResourceNotFoundException("Password not match in authontication servivce method");
		return uData;
	}
	@Override
	public UserDetailsData updateUserDetails(UserDetailsDTO updateDetails, Long id) {
		// TODO Auto-generated method stub
		
		System.out.println("in update service method : "+updateDetails +"User id: "+id );
		UserDetailsData uData=new UserDetailsData();
		uData=uRepo.findById(id).get();
		System.out.println("Detached user deatails:  "+uData);
		//uData=mapper.map(updateDetails,uData);
		BeanUtils.copyProperties(updateDetails, uData);
		System.out.println("after user updated details:  "+uData);
		return uData;
	}
	@Override
	public String deleteUser(Long id) {
		// TODO Auto-generated method stub
		
		System.out.println("In service delete :"+id);
		//StringBuilder sr=new StringBuilder("Here");
		if(id!=null)
			uRepo.deleteById(id);
		return "Deleted sucesffully user with id :"+id;
	}
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		System.out.println("In load by user nm: "+userName);
		// invoke dao's method to load user details from db by username(ie. actaully an
				// email)
		
		UserDetailsData user=uRepo.findByEmail(userName);
				if(user==null)
					new UsernameNotFoundException("Invalid Email ID : "+userName);
			
		System.out.println("Lifted user data from DB by Email : "+user);
		
		return new CustomeUserDetails(user);
	}
	

	
}