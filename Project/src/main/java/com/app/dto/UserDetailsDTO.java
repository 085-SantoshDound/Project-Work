package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.aspectj.apache.bcel.generic.Type;
import org.hibernate.validator.constraints.Length;

import com.app.entities.BaseEntity;
import com.app.entities.UserRole;
import com.app.entities.UserAddress;
import com.app.entities.UserDetailsData;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
public class UserDetailsDTO {
	//@JsonProperty("fn")
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	
	@NotBlank(message = "First Name can't be empty")
	@Length(min = 3,max = 15)
	private String firstName;
	//@JsonProperty("ln")
	@NotBlank(message = "Last Name can't be empty")
	@Length(min = 3,max = 15)
	private String lastName;
	@Length(min = 2,max = 40,message = "Invalid Email length")
	@NotBlank(message = "Email can't be empty")
	@Email(message = "Invalid email format")
	//@JsonProperty("e")
	private String email;
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message = "Invalid password!")
	@Length(min = 2,max = 40)
	//@JsonProperty("p")
	private String password;
	@NotBlank(message = "Enter MobileNo")
	//@JsonProperty("m")
	private String mobileNo;
	//@JsonProperty("r")
	@Enumerated(EnumType.STRING)
	private UserRole userRole; 
	//@JsonProperty("lm")
	@NotBlank(message = "Filed can't be empty")
	private String landmark;
	//@JsonProperty("st")
	@NotBlank(message = "Filed can't be empty")
	private String street;
	//@JsonProperty("ci")
	@Length(min = 2,max =  30)
	private String city;
	@NotBlank(message = "please Enter state")
	//@JsonProperty("stat")
	@Length(min = 2,max =  20)
	private String state;
	@NotBlank(message = "Please Enter PIN-CODE")
	//@JsonProperty("pin")
	private String pincode;	
	
}
