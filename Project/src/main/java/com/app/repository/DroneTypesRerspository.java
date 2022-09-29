package com.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.DroneTypes;

@Transactional
public interface DroneTypesRerspository extends JpaRepository<DroneTypes, Long> {
	
}
