package com.app.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user_transaction_details")
public class UserTransactionDetails extends BaseEntity{

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="trans_and_products",joinColumns = @JoinColumn(name="product_id"),inverseJoinColumns = @JoinColumn(name="transiction_id"))
	private List<Product> product;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private UserDetailsData user;
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="purchase_timendate")
	private LocalDateTime purchaseTime=LocalDateTime.now();
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="Adress_id")
	private UserAddress adress;
	@Column(name="total_price")
	private double price;
	@OneToOne
	@JoinColumn(name="payment_id")
	private Payment payment;
	@Override
	public String toString() {
		return "UserTransactionDetails [ purchaseTime=" + purchaseTime + ", adress=" + adress
				+ ", price=" + price + ", payment=" + payment + "]";
	}
	
	
}
