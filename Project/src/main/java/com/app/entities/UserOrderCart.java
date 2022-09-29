package com.app.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user_cart")
public class UserOrderCart extends BaseEntity {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private UserDetailsData uDetails;
	
	@ManyToMany
	@JoinTable(name="cart_products",
	joinColumns = @JoinColumn(name="cart_id"),inverseJoinColumns = @JoinColumn(name="product_id"))
	private List<Product> productList;
}
