package com.udemy.course.restaurantservice.app.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.udemy.course.restaurantservice.app.dto.RestaurantDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurant Controller")
public interface IRestaurantApi {

	@ApiOperation(value = "createRestaurant", notes = "")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Create"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantDto> create(
			@ApiParam(value = "RestaurantDto", required = true) 
			@Validated @RequestBody RestaurantDto restaurantDto);

	@ApiOperation(value = "findAllRestaurants", notes = "")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RestaurantDto>> findAll();

	@ApiOperation(value = "findRestaurantById", notes = "")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	@GetMapping(path = "/{restaurant-id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantDto> findById(
			@ApiParam(value = "restaurant-id", required = true)
			@PathVariable("restaurant-id") Long restaurantId);

	@ApiOperation(value = "updateRestaurant", notes = "")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	@PutMapping(path = "/{restaurant-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(
			@ApiParam(value = "restaurant-id", required = true)
			@PathVariable("restaurant-id") Long restaurantId,
			@ApiParam(value = "RestaurantDto", required = true)
			@Validated  @RequestBody RestaurantDto restaurantDto);

	@ApiOperation(value = "deleteRestaurantById", notes = "")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	@DeleteMapping(path = "/{restaurant-id}")
	public ResponseEntity<?> delete(
			@ApiParam(value = "restaurant-id", required = true)
			@PathVariable("restaurant-id") Long restaurantId);

}
