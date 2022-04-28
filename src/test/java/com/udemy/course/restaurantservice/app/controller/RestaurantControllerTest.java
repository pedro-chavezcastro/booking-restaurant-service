package com.udemy.course.restaurantservice.app.controller;

import static com.udemy.course.restaurantservice.app.DummyMock.restaurantDtoIn;
import static com.udemy.course.restaurantservice.app.DummyMock.restaurantDtoInError;
import static com.udemy.course.restaurantservice.app.DummyMock.restaurantDtoOut;
import static com.udemy.course.restaurantservice.app.DummyMock.restaurantDtoList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.course.restaurantservice.app.dto.ErrorDto;
import com.udemy.course.restaurantservice.app.dto.RestaurantDto;
import com.udemy.course.restaurantservice.app.exception.RestaurantNotFoundException;
import com.udemy.course.restaurantservice.app.service.IRestaurantService;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private IRestaurantService service;
	
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setUp() {
		this.objectMapper = new ObjectMapper();
	}
	
	@Test
	void create() throws JsonProcessingException, Exception {
		RestaurantDto restaurantIn = restaurantDtoIn();
		RestaurantDto restaurantOut = restaurantDtoOut();
		when(this.service.create(any(RestaurantDto.class))).thenReturn(restaurantOut);
		
		this.mvc.perform(post("/api-restaurant/v1/restaurants")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(asJsonString(restaurantIn)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json(asJsonString(restaurantOut)));
		
		verify(this.service, times(1)).create(any(RestaurantDto.class));
	}
	
	@Test
	void createBindException() throws JsonProcessingException, Exception {
		RestaurantDto restaurantIn = restaurantDtoInError();
		ErrorDto errorDto = new ErrorDto();
		errorDto.setCode(400);
		errorDto.setStatus(HttpStatus.BAD_REQUEST);
		errorDto.setMessage("[image:size must be between 10 and 500]");
		errorDto.toString();
		
		this.mvc.perform(post("/api-restaurant/v1/restaurants")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(asJsonString(restaurantIn)))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code").value(errorDto.getCode()))
				//.andExpect(jsonPath("$.status").value(errorDto.getStatus()))
				.andExpect(jsonPath("$.message").value(errorDto.getMessage()));
	}	
	
	@Test
	void findById() throws Exception {
		RestaurantDto restaurantOut = restaurantDtoOut();
		when(this.service.findById(anyLong())).thenReturn(restaurantOut);
		
		this.mvc.perform(get("/api-restaurant/v1/restaurants/{restaurant-id}", 1L))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(content().json(asJsonString(restaurantOut)));
		
		verify(this.service).findById(anyLong());
	}
	
	@Test
	void internalServerError() throws Exception {
		when(this.service.findById(anyLong())).thenThrow(new ArrayIndexOutOfBoundsException());
		this.mvc.perform(get("/api-restaurant/v1/restaurants/{restaurant-id}", 1L))
				.andDo(print())
				.andExpect(status().isInternalServerError());
	}	
	
	@Test
	void findByIdRestaurantNotFound() throws Exception {
		//RestaurantDto restaurantOut = restaurantDtoOut();
		when(this.service.findById(anyLong())).thenThrow(new RestaurantNotFoundException(""));
		
		this.mvc.perform(get("/api-restaurant/v1/restaurants/{restaurant-id}", 1L))
				.andDo(print())
				.andExpect(status().isBadRequest());
		
		verify(this.service).findById(anyLong());
	}	
	
	@Test
	void findAll() throws JsonProcessingException, Exception {
		List<RestaurantDto> restaurantsOut = restaurantDtoList();
		when(this.service.findAll()).thenReturn(restaurantsOut);
		
		this.mvc.perform(get("/api-restaurant/v1/restaurants"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", Matchers.hasSize(3)))
				.andExpect(content().json(asJsonString(restaurantsOut)));
		
		verify(this.service).findAll();
	}
	
	@Test
	void update() throws Exception {
		RestaurantDto restaurantIn = restaurantDtoIn();
		
		this.mvc.perform(put("/api-restaurant/v1/restaurants/{restaurant-id}", 1L)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(asJsonString(restaurantIn)))
				.andDo(print())
				.andExpect(status().isOk());
		
		verify(this.service).update(anyLong(), any(RestaurantDto.class));
	}
	
	@Test
	void deleteRestaurant() throws Exception {
		this.mvc.perform(delete("/api-restaurant/v1/restaurants/{restaurant-id}", 1L))
				.andDo(print())
				.andExpect(status().isOk());
		
		verify(this.service).delete(anyLong());
	}
	
	private String asJsonString(Object object) throws JsonProcessingException {
		return this.objectMapper.writeValueAsString(object);
	}

}
