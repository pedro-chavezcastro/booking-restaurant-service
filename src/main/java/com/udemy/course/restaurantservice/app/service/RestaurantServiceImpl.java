package com.udemy.course.restaurantservice.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.course.restaurantservice.app.dto.BoardDto;
import com.udemy.course.restaurantservice.app.dto.RestaurantDto;
import com.udemy.course.restaurantservice.app.entity.BoardEntity;
import com.udemy.course.restaurantservice.app.entity.RestaurantEntity;
import com.udemy.course.restaurantservice.app.exception.RestaurantNotFoundException;
import com.udemy.course.restaurantservice.app.repository.IRestaurantRepository;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

	@Autowired
	private IRestaurantRepository repository;

	@Override
	public RestaurantDto create(RestaurantDto restaurantDto) {
		RestaurantEntity restaurantEntityIn = this.mapInRestaurantDtoToEntity(restaurantDto);
		RestaurantEntity restaurantEntityOut = this.persist(restaurantEntityIn);
		return this.mapOutRestaurantEntityToDto(restaurantEntityOut);
	}

	private RestaurantEntity mapInRestaurantDtoToEntity(RestaurantDto restaurantDto) {
		RestaurantEntity restaurantEntity = new RestaurantEntity();
		restaurantEntity.setName(restaurantDto.getName());
		restaurantEntity.setDescription(restaurantDto.getDescription());
		restaurantEntity.setAddress(restaurantDto.getAddress());
		restaurantEntity.setImage(restaurantDto.getImage());
		restaurantEntity.setEnabled(Boolean.TRUE);
		
		restaurantDto.getBoardList().forEach(boardDto ->{
			BoardEntity boardEntity = new BoardEntity();
			boardEntity.setCapacity(boardDto.getCapacity());
			boardEntity.setNumber(boardDto.getNumber());
			restaurantEntity.addBoard(boardEntity);
		});
		
		return restaurantEntity;
	}

	private RestaurantEntity persist(RestaurantEntity restaurantEntityIn) {
		return this.repository.save(restaurantEntityIn);
	}

	private RestaurantDto mapOutRestaurantEntityToDto(RestaurantEntity restaurantEntity) {
		RestaurantDto restaurantDto =new RestaurantDto();
		restaurantDto.setId(restaurantEntity.getId());
		restaurantDto.setName(restaurantEntity.getName());
		restaurantDto.setDescription(restaurantEntity.getDescription());
		restaurantDto.setAddress(restaurantEntity.getAddress());
		restaurantDto.setImage(restaurantEntity.getImage());
		restaurantDto.setEnabled(restaurantEntity.getEnabled());
		
		if (restaurantEntity.getBoardList() != null) {
			List<BoardDto> boardDtoList = restaurantEntity.getBoardList().stream()
					.map(this::mapOutBoardEntityToDto)
					.collect(Collectors.toList());	
			restaurantDto.setBoardList(boardDtoList);
		}
		
		return restaurantDto;
	}
	
	private BoardDto mapOutBoardEntityToDto(BoardEntity boardEntity) {
		BoardDto boardDto= new BoardDto();
		boardDto.setId(boardEntity.getId());
		boardDto.setCapacity(boardEntity.getCapacity());
		boardDto.setNumber(boardEntity.getNumber());
		return boardDto;
	}
	
	@Override
	public RestaurantDto findById(Long restaurantId) {
		RestaurantEntity restaurantEntity = this.findRestaurantEntityById(restaurantId);
		return this.mapOutRestaurantEntityToDto(restaurantEntity);
	}

	private RestaurantEntity findRestaurantEntityById(Long restaurantId) {
		return this.repository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(
				String.format("El restaurante con id %s no existe.", restaurantId)));
	}

	@Override
	public List<RestaurantDto> findAll() {
		List<RestaurantEntity> restaurantEntityList = this.repository.findAllByEnabled(Boolean.TRUE);
		return restaurantEntityList.stream().map(this::mapOutRestaurantEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public void update(Long restaurantId, RestaurantDto restaurantDto) {
		RestaurantEntity restaurantEntity = this.findRestaurantEntityById(restaurantId);
		restaurantEntity.setName(restaurantDto.getName());
		restaurantEntity.setDescription(restaurantDto.getDescription());
		restaurantEntity.setAddress(restaurantDto.getAddress());
		restaurantEntity.setImage(restaurantDto.getImage());
		restaurantEntity.setEnabled(restaurantDto.getEnabled());
		this.persist(restaurantEntity);

	}

	@Override
	public void delete(Long restaurantId) {
		RestaurantEntity restaurantEntity = this.findRestaurantEntityById(restaurantId);
		restaurantEntity.setEnabled(false);
		this.persist(restaurantEntity);

	}

}
