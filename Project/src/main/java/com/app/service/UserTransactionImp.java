package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.UserTransactionDetails;
import com.app.repository.UserTransactionRepository;

@Service
@Transactional
public class UserTransactionImp implements IUserTransaction{

	
	@Autowired
	private UserTransactionRepository transRepo;
	
	@Override
	public UserTransactionDetails getTransaction(Long id) {
		System.out.println("in service og get transaction method :"+id);
		return transRepo.findById(id).get();
	}

}
