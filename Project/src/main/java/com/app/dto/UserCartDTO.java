package com.app.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.app.entities.DroneTypes;
import com.app.entities.Product;
import com.app.entities.UserDetailsData;
import com.app.entities.UserOrderCart;
import com.app.entities.UserTransactionDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserCartDTO {

	
	private String productName;
	
	
	private double price;
	
	
	private String brand;
	
	
	private int quantity;
	
	private String imgPath;
	
	private DroneTypes droneType;
	
	private LocalDate localDate;
	
}
