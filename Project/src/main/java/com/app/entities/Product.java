package com.app.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseEntity {
	@Column(name="product_name",nullable=false)
	private String productName;
	
	@Column(length=15,nullable=false)
	private double price;
	
	@Column(nullable=false)
	//@DroneTypes(type = "text")
	@Lob
	private String description;
	
	@Column(length=15,nullable=false)
	private String brand;
	
	@Column(name="product_quantity")
	private int quantity;
	
	@Column(name="image_path")
	private String imgPath;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="product_type",
	joinColumns = @JoinColumn(name="dronetype_id"), inverseJoinColumns = @JoinColumn(name="product_id"))
	private DroneTypes droneType;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private UserTransactionDetails transProducts;
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<UserOrderCart> cart;
}

