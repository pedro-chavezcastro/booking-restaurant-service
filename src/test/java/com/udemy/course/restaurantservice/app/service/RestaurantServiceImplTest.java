package com.udemy.course.restaurantservice.app.service;

import static com.udemy.course.restaurantservice.app.DummyMock.restaurantDtoIn;
import static com.udemy.course.restaurantservice.app.DummyMock.restaurantDtoList;
import static com.udemy.course.restaurantservice.app.DummyMock.restaurantDtoOut;
import static com.udemy.course.restaurantservice.app.DummyMock.restaurantEntityList;
import static com.udemy.course.restaurantservice.app.DummyMock.restaurantEntityOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.udemy.course.restaurantservice.app.dto.RestaurantDto;
import com.udemy.course.restaurantservice.app.entity.RestaurantEntity;
import com.udemy.course.restaurantservice.app.exception.RestaurantNotFoundException;
import com.udemy.course.restaurantservice.app.repository.IRestaurantRepository;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceImplTest {

	@Mock
	private IRestaurantRepository repository;
	
	@InjectMocks
	private RestaurantServiceImpl service;
	
	@Test
	void createOk() {
		RestaurantDto restaurantDtoIn = restaurantDtoIn();
		RestaurantEntity restaurantEntityOut = restaurantEntityOut();
		
		when(this.repository.save(any(RestaurantEntity.class))).thenReturn(restaurantEntityOut);
		
		RestaurantDto restaurantExpected = this.service.create(restaurantDtoIn);
		
		verify(this.repository).save(any(RestaurantEntity.class));
				
		assertNotNull(restaurantExpected);
		assertEquals(1L, restaurantExpected.getId());
		assertEquals(restaurantDtoIn.getName(), restaurantExpected.getName());
		assertEquals(restaurantDtoIn.getDescription(), restaurantExpected.getDescription());
		assertEquals(restaurantDtoIn.getAddress(), restaurantExpected.getAddress());
		assertEquals(restaurantDtoIn.getImage(), restaurantExpected.getImage());
		assertEquals(restaurantDtoIn.getEnabled(), restaurantExpected.getEnabled());
	}
	
	@Test
	void findByIdOk() {
		Long restaurantId = 1L;
		RestaurantEntity restaurantEntityOut = restaurantEntityOut();
		RestaurantDto restaurantExpected = restaurantDtoOut();
		
		when(this.repository.findById(anyLong())).thenReturn(Optional.of(restaurantEntityOut));
		
		RestaurantDto restaurantActual = this.service.findById(restaurantId);
		
		verify(this.repository).findById(anyLong());
				
		assertNotNull(restaurantActual);
		assertEquals(restaurantExpected.getId(), restaurantActual.getId());
		assertEquals(restaurantExpected.getName(), restaurantActual.getName());
		assertEquals(restaurantExpected.getDescription(), restaurantActual.getDescription());
		assertEquals(restaurantExpected.getAddress(), restaurantActual.getAddress());
		assertEquals(restaurantExpected.getImage(), restaurantActual.getImage());
		assertEquals(restaurantExpected.getEnabled(), restaurantActual.getEnabled());
		assertEquals(restaurantExpected.getBoardList().get(0).getId(), restaurantActual.getBoardList().get(0).getId());
		assertEquals(restaurantExpected.getBoardList().get(0).getCapacity(), restaurantActual.getBoardList().get(0).getCapacity());
		assertEquals(restaurantExpected.getBoardList().get(0).getNumber(), restaurantActual.getBoardList().get(0).getNumber());
	}
	
	@Test
	void findByIdRestaurantNotFoud() {
		when(this.repository.findById(anyLong())).thenThrow(RestaurantNotFoundException.class);
		
		Long restaurantId = 1L;
		assertThrows(RestaurantNotFoundException.class, () -> {
			this.service.findById(restaurantId);
		});
		
		verify(this.repository).findById(anyLong());				
	}
	
	@Test
	void findAllOk() {
		List<RestaurantEntity> restaurantEntityList = restaurantEntityList();
		when(this.repository.findAllByEnabled(anyBoolean())).thenReturn(restaurantEntityList);
		
		List<RestaurantDto> restaurantDtoExpected = restaurantDtoList();
		List<RestaurantDto> restaurantDtoActual = this.service.findAll();
		
		verify(this.repository).findAllByEnabled(anyBoolean());
		
		assertNotNull(restaurantDtoActual);
		assertEquals(restaurantDtoExpected, restaurantDtoActual);
	}
	
	@Test
	void update() {
		Long restaurantId = 1L;
		RestaurantEntity restaurantEntityOut = restaurantEntityOut();
		when(this.repository.findById(anyLong())).thenReturn(Optional.of(restaurantEntityOut));
		this.service.update(restaurantId, restaurantDtoIn());
		verify(this.repository).findById(anyLong());
		verify(this.repository).save(any(RestaurantEntity.class));
	}
	
	@Test
	void deleteOk() {
		Long restaurantId = 1L;
		RestaurantEntity restaurantEntityOut = restaurantEntityOut();
		when(this.repository.findById(anyLong())).thenReturn(Optional.of(restaurantEntityOut));
		this.service.delete(restaurantId);
		verify(this.repository).findById(anyLong());
	}

}
