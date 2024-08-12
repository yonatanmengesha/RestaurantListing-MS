package com.jotech.restaurantlisting.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotech.restaurantlisting.dto.RestaurantDto;
import com.jotech.restaurantlisting.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping("/fetchAllRestaurants")
	public ResponseEntity<List<RestaurantDto>> fetchAllRestaurants(){
		
	List<RestaurantDto>  allRestaurants =	restaurantService.findAllRestaurants();
	
	
	return new ResponseEntity<>(allRestaurants,HttpStatus.OK);
		
	}
	
	
	@PostMapping("/addRestaurant")
	public ResponseEntity<RestaurantDto> saveRestaurant(@RequestBody RestaurantDto restaurantDto){
		
		RestaurantDto restaurantAdded = restaurantService.addRestaurantInDB(restaurantDto);
		
		
		return new ResponseEntity<>(restaurantAdded,HttpStatus.CREATED);
		
		
	}
	
	
	@GetMapping("/fetchById/{id}")
	public ResponseEntity<RestaurantDto> findRestaurantById(@PathVariable Integer id){
		
		return restaurantService.fetchRestaurantById(id);
		
		
		
	   
		
	}

}
