package com.udemy.course.restaurantservice.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "board")
@Getter @Setter @NoArgsConstructor
public class BoardEntity {

	@Id
	@SequenceGenerator(name = "board_id_seq", sequenceName = "board_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_id_seq")
	@Column(name = "id" , nullable = false)
	private Long id;
	
	@Column(name = "capacity", nullable = false)
	private Long capacity;
	
	@Column(name = "number", nullable = false)
	private Long number;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_id", nullable = false)
	private RestaurantEntity restaurant;

}
