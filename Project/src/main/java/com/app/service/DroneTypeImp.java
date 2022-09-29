package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.DroneTypeDTO;
import com.app.entities.DroneTypes;
import com.app.repository.DroneTypesRerspository;

@Service
@Transactional
public class DroneTypeImp implements IDroneTypeService {
	@Autowired
	private DroneTypesRerspository droneRepo;
	
	@Autowired
	private ModelMapper mapper;

	
	@Override
	public String addDronetype(DroneTypeDTO droneType) {
		
		System.out.println("in service implementation method"+droneType);
		DroneTypes trans=mapper.map(droneType, DroneTypes.class);
		DroneTypes drone=droneRepo.save(trans);
		System.out.println("drone");
		return "Sucessfully inserted";
	}


}
