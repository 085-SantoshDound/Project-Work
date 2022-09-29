package com.app.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="user_address")
@Getter
@Setter
//@ToString(exclude = "user")
public class UserAddress extends BaseEntity {
	
	
	
	@Column(name= "add_landmark", length=30)
	
	private String landmark;
	
	@Column(name="street_name", length=80)
	private String street;

	@Column(name="user_city", length=30)
	private String city;
	
	@Column(name="user_state", length=30)
	private String state;

	@Column(name="user_pincode", length=30, nullable=false)
	private String pincode;
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "userAddres")
	private UserDetailsData user;
	@Override
	public String toString() {
		return "UserAddress [landmark=" + landmark + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + ", getId()=" + getId() + "]";
	}
	
}