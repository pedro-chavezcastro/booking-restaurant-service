package com.udemy.course.restaurantservice.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static com.udemy.course.restaurantservice.app.DummyMock.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.udemy.course.restaurantservice.app.entity.RestaurantEntity;

@DataJpaTest
class RestaurantRepositoryTest {

	@Autowired
	private IRestaurantRepository repository;
	
	@Test
	void findById() {
		Optional<RestaurantEntity> restaurant = this.repository.findById(1L);
		assertTrue(restaurant.isPresent());
	}
	
	@Test
	void findAll() {
		List<RestaurantEntity> restaurantList = this.repository.findAll();
		assertFalse(restaurantList.isEmpty());
		assertEquals(4, restaurantList.size());
	}
	
	@Test
	void save() {
		RestaurantEntity restaurant = restaurantEntityIn();
		RestaurantEntity restaurantSave = this.repository.save(restaurant);
		
		assertNotNull(restaurantSave);
	}
	
	@Test
	void update() {
		Optional<RestaurantEntity> restaurant = this.repository.findById(1L);
		assertTrue(restaurant.isPresent());		
		
		RestaurantEntity restaurantEntity = restaurant.orElseThrow();
		assertEquals("KFC", restaurantEntity.getName());
		
		restaurantEntity.setName("KFC2");
		
		RestaurantEntity restaurantUpdate = this.repository.save(restaurantEntity);
		
		assertNotNull(restaurantUpdate);
		assertEquals("KFC2", restaurantEntity.getName());
	}
}
