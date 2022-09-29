package com.app.service;

import javax.validation.Valid;

import com.app.dto.DroneTypeDTO;
import com.app.dto.AuthRequest;
import com.app.entities.UserDetailsData;

public interface IAdminService {

	UserDetailsData adminAuthontication(AuthRequest login);
}
