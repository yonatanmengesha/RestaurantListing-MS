package com.jotech.restaurantlisting.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jotech.restaurantlisting.dto.RestaurantDto;
import com.jotech.restaurantlisting.service.RestaurantService;

public class RestaurantControllerTest {
	
	
	@InjectMocks
	RestaurantController restaurantController;
	
	@Mock
	RestaurantService restaurantService;
	
	@BeforeEach
	public void setUp() {
		
		MockitoAnnotations.openMocks(this);//in order for Mock and InjectMocks annotations to take effect, you need to call MockitoAnnotations.openMocks(this);
		
		
	}
	
	@Test
	public void testFetchAllRestaurants() {
		
		//Mock the service behavior 
		
		RestaurantDto  restaurantDto1 = new RestaurantDto(1,"Restaurant 1", "Address 1", "city 1", "Desc 1");
		RestaurantDto  restaurantDto2 = new RestaurantDto(2,"Restaurant 2", "Address 2", "city 2", "Desc 2");
		
		
		List<RestaurantDto> mockRestaurants =  new ArrayList<>();
		
		mockRestaurants.add(restaurantDto1);
		mockRestaurants.add(restaurantDto2);
		
		when(restaurantService.findAllRestaurants()).thenReturn(mockRestaurants);
		
		// call the controller method 
		
		ResponseEntity<List<RestaurantDto>> response = restaurantController.fetchAllRestaurants();
		
		//verify the response 
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(mockRestaurants,response.getBody());
		
		
		//verify that the service method was called 
		
		
		verify(restaurantService,times(1)).findAllRestaurants();
				
				
								
				
				
		
		
	}
	
	@Test
	public void testSaveRestaurant() {
		
		// Create a mock restaurant to be saved
		
		RestaurantDto mockRestaurant = new RestaurantDto(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
		
		// Mock the service behavior
		when(restaurantService.addRestaurantInDB(mockRestaurant)).thenReturn(mockRestaurant);
		
		// Call the controller method
		
		ResponseEntity<RestaurantDto> response = restaurantController.saveRestaurant(mockRestaurant);
		
		
		  // Verify the response
		
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		
		assertEquals(mockRestaurant , response.getBody());
		
		 // Verify that the service method was called
		
		verify(restaurantService,times(1)).addRestaurantInDB(mockRestaurant);
		
	}
	
	@Test
	public void testFindRestaurantById() {
		
		  // Create a mock restaurant ID
		Integer mockRestaurantId =1;
		
		// Create a mock restaurant to be returned by the service
		RestaurantDto mockRestaurant = new RestaurantDto(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
		

        // Mock the service behavior
		when(restaurantService.fetchRestaurantById(mockRestaurantId))
		.thenReturn(new ResponseEntity<>(mockRestaurant,HttpStatus.OK));
		
		//Call the controller method
		
		ResponseEntity<RestaurantDto> response = restaurantController.findRestaurantById(mockRestaurantId);
		
		// Verify the response
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(mockRestaurant,response.getBody());
		
		 // Verify that the service method was called
		
		verify(restaurantService,times(1)).fetchRestaurantById(mockRestaurantId);
		
	}
	
	

}
