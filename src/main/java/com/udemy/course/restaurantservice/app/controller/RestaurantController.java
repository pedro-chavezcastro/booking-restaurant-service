package com.udemy.course.restaurantservice.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.course.restaurantservice.app.api.IRestaurantApi;
import com.udemy.course.restaurantservice.app.dto.RestaurantDto;
import com.udemy.course.restaurantservice.app.service.IRestaurantService;

@RestController
@RequestMapping(path = "api-restaurant/v1/restaurants")
public class RestaurantController implements IRestaurantApi {
	
	@Autowired
	private IRestaurantService service;

	@Override
	public ResponseEntity<RestaurantDto> create(RestaurantDto restaurantDto) {
		RestaurantDto response = this.service.create(restaurantDto);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<List<RestaurantDto>> findAll() {
		List<RestaurantDto> response = this.service.findAll();
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<RestaurantDto> findById(Long restaurantId) {
		RestaurantDto response = this.service.findById(restaurantId);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?> update(Long restaurantId, RestaurantDto restaurantDto) {
		this.service.update(restaurantId, restaurantDto);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> delete(Long restaurantId) {
		this.service.delete(restaurantId);
		return ResponseEntity.ok().build();
	}

}
