package com.app.entities;

import javax.persistence.Column;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.app.dto.UserDetailsDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users_details")
@Getter
@Setter
public class UserDetailsData extends BaseEntity{
	
	@Column(name="first_name",length=15)
	private String firstName;
	@Column(name="last_name",length=15)
	private String lastName;
	@Column(length=45, unique = true)
	private String email;
	private String password;
	@Column(name="mobile_no")
	private String mobileNo;
	@Enumerated(EnumType.STRING)
	@Column(name ="user_role")
	private UserRole userRole;
	@OneToOne(cascade = CascadeType.ALL)
 	@JoinColumn(name = "add_id")
	@JsonBackReference
	private UserAddress userAddres;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
	@JsonBackReference
	private List<UsersOrder> orders;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
	@JsonBackReference
	private List<UserTransactionDetails> tranDetails;
	@OneToOne(mappedBy = "uDetails")
	@JsonBackReference
	private UserOrderCart cart;
	@Override
	public String toString() {
		return "UserDetailsData [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", mobileNo=" + mobileNo + ", userRole=" + userRole + ", getId()=" +  "]";
	}
}
