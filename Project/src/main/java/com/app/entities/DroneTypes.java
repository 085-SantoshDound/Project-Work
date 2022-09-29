package com.app.entities;


import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="drone_types")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DroneTypes extends BaseEntity {
	@Column(name="type",length=50, unique = true)
	private String droneType;
}


