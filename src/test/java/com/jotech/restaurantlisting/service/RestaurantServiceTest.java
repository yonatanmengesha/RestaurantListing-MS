package com.jotech.restaurantlisting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jotech.restaurantlisting.dto.RestaurantDto;
import com.jotech.restaurantlisting.entity.Restaurant;
import com.jotech.restaurantlisting.repo.RestaurantRepo;

import com.jotech.restaurantlisting.mapper.RestaurantMapper;

public class RestaurantServiceTest {
	
	@Mock
	RestaurantRepo restaurantRepo;
	
	@InjectMocks
	RestaurantService restaurantService;
	
	@BeforeEach
	public void setUp() {
		
		MockitoAnnotations.openMocks(this);//in order for Mock and InjectMocks annotations to take effect, you need to call MockitoAnnotations.openMocks(this);
		
	}
	
	@Test
	public void testFindAllRestaurants() throws ClassNotFoundException{
		// Create mock restaurants
        List<Restaurant> mockRestaurants = Arrays.asList(
                new Restaurant(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
                new Restaurant(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
        );
        when(restaurantRepo.findAll()).thenReturn(mockRestaurants);

        // Call the service method
        List<RestaurantDto> restaurantDTOList = restaurantService.findAllRestaurants();

        // Verify the result
        assertEquals(mockRestaurants.size(), restaurantDTOList.size());
        for (int i = 0; i < mockRestaurants.size(); i++) {
        	RestaurantDto expectedDTO = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(mockRestaurants.get(i));
            assertEquals(expectedDTO, restaurantDTOList.get(i));
        }

        // Verify that the repository method was called
        verify(restaurantRepo, times(1)).findAll();}

}
