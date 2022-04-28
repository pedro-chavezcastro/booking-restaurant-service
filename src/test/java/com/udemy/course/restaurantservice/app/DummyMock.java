package com.udemy.course.restaurantservice.app;

import java.util.Arrays;
import java.util.List;

import com.udemy.course.restaurantservice.app.dto.BoardDto;
import com.udemy.course.restaurantservice.app.dto.RestaurantDto;
import com.udemy.course.restaurantservice.app.entity.BoardEntity;
import com.udemy.course.restaurantservice.app.entity.RestaurantEntity;

public class DummyMock {

	public static RestaurantEntity restaurantEntityIn() {
		RestaurantEntity restaurant = new RestaurantEntity();
		restaurant.setName("Seratta Gourmand Market");
		restaurant.setDescription("Seratta es un parque temático que tiene infinidad de experiencias gastronómicas.");
		restaurant.setAddress("Cra. 45 #114-44, Bogotá, DC");
		restaurant.setImage("imageimageimage");
		restaurant.setEnabled(Boolean.TRUE);
		return restaurant;
	}
	
	public static RestaurantEntity restaurantEntityOut() {
		RestaurantEntity restaurant = new RestaurantEntity();
		restaurant.setId(1L);
		restaurant.setName("Seratta Gourmand Market");
		restaurant.setDescription("Seratta es un parque temático que tiene infinidad de experiencias gastronómicas.");
		restaurant.setAddress("Cra. 45 #114-44, Bogotá, DC");
		restaurant.setImage("imageimageimageimageimageimage");
		restaurant.setEnabled(Boolean.TRUE);
		restaurant.setBoardList(boardEntityList());
		return restaurant;
	}
	
	public static List<BoardEntity> boardEntityList(){
		BoardEntity boardDto = new BoardEntity();
		boardDto.setId(1L);
		boardDto.setCapacity(2L);
		boardDto.setNumber(1L);
		
		BoardEntity boardDto2 = new BoardEntity();
		boardDto.setId(2L);
		boardDto.setCapacity(2L);
		boardDto.setNumber(2L);
		
		BoardEntity boardDto3 = new BoardEntity();
		boardDto.setId(3L);
		boardDto.setCapacity(3L);
		boardDto.setNumber(3L);
		
		return Arrays.asList(boardDto, boardDto2, boardDto3);
	}	
	
	public static RestaurantDto restaurantDtoIn() {
		RestaurantDto restaurant= new RestaurantDto();
		restaurant.setName("Seratta Gourmand Market");
		restaurant.setDescription("Seratta es un parque temático que tiene infinidad de experiencias gastronómicas.");
		restaurant.setAddress("Cra. 45 #114-44, Bogotá, DC");
		restaurant.setImage("imageimageimageimageimageimage");
		restaurant.setEnabled(Boolean.TRUE);
		restaurant.setBoardList(boardDtoList());
		return restaurant;
	}
	
	public static RestaurantDto restaurantDtoInError() {
		RestaurantDto restaurant= new RestaurantDto();
		restaurant.setName("Seratta Gourmand Market");
		restaurant.setDescription("Seratta es un parque temático que tiene infinidad de experiencias gastronómicas.");
		restaurant.setAddress("Cra. 45 #114-44, Bogotá, DC");
		restaurant.setImage("image");
		restaurant.setEnabled(Boolean.TRUE);
		restaurant.setBoardList(boardDtoList());
		return restaurant;
	}	
	
	public static List<BoardDto> boardDtoList(){
		BoardDto boardDto = new BoardDto();
		boardDto.setCapacity(2L);
		boardDto.setNumber(1L);
		
		BoardDto boardDto2 = new BoardDto();
		boardDto.setCapacity(2L);
		boardDto.setNumber(2L);
		
		BoardDto boardDto3 = new BoardDto();
		boardDto.setCapacity(3L);
		boardDto.setNumber(3L);
		
		return Arrays.asList(boardDto, boardDto2, boardDto3);
	}
	
	public static RestaurantDto restaurantDtoOut() {
		RestaurantDto restaurant = new RestaurantDto();
		restaurant.setId(1L);
		restaurant.setName("Seratta Gourmand Market");
		restaurant.setDescription("Seratta es un parque temático que tiene infinidad de experiencias gastronómicas.");
		restaurant.setAddress("Cra. 45 #114-44, Bogotá, DC");
		restaurant.setImage("imageimageimageimageimageimage");
		restaurant.setEnabled(Boolean.TRUE);
		restaurant.setBoardList(boardDtoListOut());
		return restaurant;
	}
	
	public static List<BoardDto> boardDtoListOut(){
		BoardDto boardDto = new BoardDto();
		boardDto.setId(1L);
		boardDto.setCapacity(2L);
		boardDto.setNumber(1L);
		
		BoardDto boardDto2 = new BoardDto();
		boardDto.setId(2L);
		boardDto.setCapacity(2L);
		boardDto.setNumber(2L);
		
		BoardDto boardDto3 = new BoardDto();
		boardDto.setId(3L);
		boardDto.setCapacity(3L);
		boardDto.setNumber(3L);
		
		return Arrays.asList(boardDto, boardDto2, boardDto3);
	}
	
	public static List<RestaurantDto> restaurantDtoList(){
		RestaurantDto restaurant1 = new RestaurantDto();
		restaurant1.setId(1L);
		restaurant1.setName("Restaurante La Parrilla Del Chicamocha");
		restaurant1.setDescription("Restaurante La Parrilla Del Chicamocha Engativa");
		restaurant1.setAddress("Cra. 118a #63k-04, Bogotá");
		restaurant1.setImage("image");
		restaurant1.setEnabled(Boolean.TRUE);
		
		RestaurantDto restaurant2 = new RestaurantDto();
		restaurant2.setId(2L);
		restaurant2.setName("Bocatoma Comida Casual");
		restaurant2.setDescription("Bocatoma Comida Casual");
		restaurant2.setAddress("Tv. 113f #67b09, Bogotá");
		restaurant1.setImage("image");
		restaurant1.setEnabled(Boolean.TRUE);
		
		RestaurantDto restaurant3 = new RestaurantDto();
		restaurant3.setId(3L);
		restaurant3.setName("Restaurante Republik Bogotá");
		restaurant3.setDescription("Restaurante Republik Bogotá");
		restaurant3.setAddress("Ac. 26 #69B-53, Bogotá, Cundinamarca");
		restaurant1.setImage("image");
		restaurant1.setEnabled(Boolean.TRUE);
		
		return Arrays.asList(restaurant1, restaurant2, restaurant3);
	}
	
	public static List<RestaurantEntity> restaurantEntityList(){
		RestaurantEntity restaurant1 = new RestaurantEntity();
		restaurant1.setId(1L);
		restaurant1.setName("Restaurante La Parrilla Del Chicamocha");
		restaurant1.setDescription("Restaurante La Parrilla Del Chicamocha Engativa");
		restaurant1.setAddress("Cra. 118a #63k-04, Bogotá");
		restaurant1.setImage("image");
		restaurant1.setEnabled(Boolean.TRUE);
		
		RestaurantEntity restaurant2 = new RestaurantEntity();
		restaurant2.setId(2L);
		restaurant2.setName("Bocatoma Comida Casual");
		restaurant2.setDescription("Bocatoma Comida Casual");
		restaurant2.setAddress("Tv. 113f #67b09, Bogotá");
		restaurant1.setImage("image");
		restaurant1.setEnabled(Boolean.TRUE);
		
		RestaurantEntity restaurant3 = new RestaurantEntity();
		restaurant3.setId(3L);
		restaurant3.setName("Restaurante Republik Bogotá");
		restaurant3.setDescription("Restaurante Republik Bogotá");
		restaurant3.setAddress("Ac. 26 #69B-53, Bogotá, Cundinamarca");
		restaurant1.setImage("image");
		restaurant1.setEnabled(Boolean.TRUE);
		
		return Arrays.asList(restaurant1, restaurant2, restaurant3);
	}
}
