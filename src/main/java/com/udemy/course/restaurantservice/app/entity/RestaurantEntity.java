package com.udemy.course.restaurantservice.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "restaurant")
@Getter @Setter @ToString
public class RestaurantEntity {

	@Id
	@SequenceGenerator(name = "restaurant_id_seq", sequenceName = "restaurant_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "address", nullable = false)
	private String address;
	@Column(name = "image", nullable = true)
	private String image;
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<BoardEntity> boardList;
	
	public void addBoard(BoardEntity board) {
		if (this.boardList  == null)
			this.boardList = new ArrayList<>();
		this.boardList.add(board);
		board.setRestaurant(this);
	}

}
