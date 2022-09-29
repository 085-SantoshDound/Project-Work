package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ErrorResponse;
import com.app.service.IUserTransaction;

@RestController
@RequestMapping("/order")
public class OrderTransactionController {
	
	@Autowired
	private IUserTransaction iTransaction;

	public OrderTransactionController()
	{
		System.out.println("in ordertransaction controller");
	}
	
	@GetMapping("/transaction/grt/{orderid}")
	public ResponseEntity<?> getTransaction(@PathVariable Long id,BindingResult br)
	{
		System.out.println("In transaction get method");
		System.out.println(" transaction :"+id);
		if(id==null)
			return new ResponseEntity<>(new ErrorResponse("invalid input"),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(iTransaction.getTransaction(id),HttpStatus.FOUND);
	}
	@GetMapping("/check")
	public String check()
	{
		return "working fine";
	}
}
