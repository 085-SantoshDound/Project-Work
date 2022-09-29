package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.DroneTypeDTO;
import com.app.dto.ErrorResponse;
import com.app.service.IDroneTypeService;

@RestController
@RequestMapping("/dronetype")
public class DroneTypeController {
	
	@Autowired
	private IDroneTypeService droneTypeService;
	
	public DroneTypeController()
	{
		System.out.println("In drone type controller");
	}
	@PostMapping("/add")
	public ResponseEntity<?> putCategory(@RequestBody @Valid DroneTypeDTO droneType, BindingResult br)
	{
		System.out.println("In catagory method for types of catagory");
		System.out.println("DroneTypes :"+droneType);
		if(droneType==null)
			throw new ResourceNotFoundException("Invalid input for drone type");
		if(br.hasFieldErrors()) {
			System.out.println("in erro");
			return new ResponseEntity<>(new ErrorResponse("Invalid Drone type"),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(droneTypeService.addDronetype(droneType),HttpStatus.ACCEPTED);
	}
}
