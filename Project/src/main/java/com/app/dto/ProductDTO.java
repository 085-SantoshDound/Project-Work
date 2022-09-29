package com.app.dto;


import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import com.app.entities.DroneTypes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ProductDTO {

	@NotBlank(message = "Product name is required ")
	private String productName;
	//@NotBlank(message = "Product price is required ")
	private double price;
	
	@NotBlank
	@Length(min = 5, max = 500)
	private String description;
	
	@NotBlank(message = "Brand name is required")
	private String brand;
	
	private int quantity;
	
	private String imgPath;
	
	//@NotBlank(message = "required field")
	private DroneTypes droneType; 
}
