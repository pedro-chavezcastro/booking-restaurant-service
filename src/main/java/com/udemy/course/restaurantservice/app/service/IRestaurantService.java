package com.udemy.course.restaurantservice.app.service;

import java.util.List;

import com.udemy.course.restaurantservice.app.dto.RestaurantDto;

public interface IRestaurantService {

	public RestaurantDto create(RestaurantDto restaurantDto);

	public RestaurantDto findById(Long restaurantId);

	public List<RestaurantDto> findAll();

	public void update(Long restaurantId, RestaurantDto restaurantDto);

	public void delete(Long restaurantId);

}
