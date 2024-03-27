package com.jotech.restaurantlisting.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int id;
	private String name;
	private String address;
	private String city;
	private String restaurantDescription;

}
