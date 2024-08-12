package com.jotech.restaurantlisting.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jotech.restaurantlisting.dto.RestaurantDto;
import com.jotech.restaurantlisting.entity.Restaurant;
import com.jotech.restaurantlisting.mapper.RestaurantMapper;
import com.jotech.restaurantlisting.repo.RestaurantRepo;

@Service
public class RestaurantService {
	
	@Autowired
	RestaurantRepo restaurantRpo;

	public List<RestaurantDto> findAllRestaurants() {
		
		 List<Restaurant> restaurants = restaurantRpo.findAll();
	        List<RestaurantDto> restaurantDTOList = restaurants.stream().map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant)).collect(Collectors.toList());
	        return restaurantDTOList;
//		List<Restaurant> restaurants = restaurantRpo.findAll();
//		
//		// map it to the list of Dtos 
//		
//		List<RestaurantDto> restaurantDtoList = restaurants.stream().map(restaurant -> RestaurantMapper.INSTANCE
//		                            		  .mapRestaurantToRestaurantDto(restaurant)).collect(Collectors.toList());
//		
//		return restaurantDtoList;
	}

	public RestaurantDto addRestaurantInDB(RestaurantDto restaurantDto) {
		
		Restaurant savedRestaurant =  restaurantRpo.save(RestaurantMapper.INSTANCE
				.mapRestaurantDtoToRestaurant(restaurantDto));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(savedRestaurant);
		
//		Restaurant restaurantDtoToRestaurant = RestaurantMapper.INSTANCE.mapRestaurantDtoToRestaurant(restaurantDto);
//		
//		Restaurant   savedRestaurant= restaurantRpo.save(restaurantDtoToRestaurant);
//		RestaurantDto  savedRestaurantToRestaurantDto = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(savedRestaurant);
//		return savedRestaurantToRestaurantDto;
	}

	public ResponseEntity<RestaurantDto> fetchRestaurantById(Integer id) {
		
		Optional<Restaurant> restaurant = restaurantRpo.findById(id);
		
		if(restaurant.isPresent()) {
			
			return new ResponseEntity<>( RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant.get()),HttpStatus.OK);
		}
		
		
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}

}
