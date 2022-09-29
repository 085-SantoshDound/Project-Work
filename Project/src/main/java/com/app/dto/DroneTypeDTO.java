package com.app.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DroneTypeDTO {
	
	@NotBlank(message = "PLs provied type of drone")
	//@Size(message = )
	@Length(min=5 ,max=100)
	private String droneType;
}
