package com.udemy.course.restaurantservice.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.course.restaurantservice.app.entity.RestaurantEntity;

@Repository
public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

	List<RestaurantEntity> findAllByEnabled(Boolean enabled);

}
