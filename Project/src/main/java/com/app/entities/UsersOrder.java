package com.app.entities;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="User_orders_tbl")
@Getter
@Setter
public class UsersOrder extends BaseEntity {

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="product_orders",
	joinColumns=@JoinColumn(name="order_id"),inverseJoinColumns = @JoinColumn(name="product_id"))
	//@JoinColumn(name="products_id")
	private List<Product> products;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UsersOrder user;  
	
	@Column(name="purchase_date")
	private LocalDate purchaseDate;
	@Column(name="delivery_date")
	private LocalDate deliveryDate;
	
	@Column(name="oreder_price")
	private double price;
	
	@Column(name="orders_quantity")
	private int quantity;
	
	@Override
	public String toString() {
		return "UsersOrder [purchaseDate=" + purchaseDate + ", deliveryDate=" + deliveryDate + ", price=" + price
				+ ", quantity=" + quantity + ", getId()=" + getId() + "]";
	}
	
	
}
