package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="payment_details")
public class Payment extends BaseEntity {

	@Column(name="payment_mode")
	@Enumerated(EnumType.STRING)
	private PaymentMode paymentMode;
	private LocalDateTime paymentTiming;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "payment") 
	private UserTransactionDetails transaction;
}
