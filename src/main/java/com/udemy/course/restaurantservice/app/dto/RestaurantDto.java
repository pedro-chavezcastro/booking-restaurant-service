package com.udemy.course.restaurantservice.app.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter @Setter
@EqualsAndHashCode
public class RestaurantDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "identificador del restaurante", readOnly = true, required = false)
	private Long id;

	@NotNull
	@NotBlank
	@Size(min = 5, max = 50)
	@ApiModelProperty(value = "nombre del restaurante", required = true)
	private String name;

	@NotNull
	@NotBlank
	@Size(min = 5, max = 100)
	@ApiModelProperty(value = "descripción", required = true)
	private String description;

	@NotNull
	@NotBlank
	@Size(min = 8, max = 30)
	@ApiModelProperty(value = "dirección del restaurante", required = true)
	private String address;

	@NotNull
	@NotBlank
	@Size(min = 10, max = 500)
	@ApiModelProperty(value = "una imagen del restaurante", required = true)
	private String image;

	@ApiModelProperty(value = "indica eliminación logica del restaurante", readOnly = true, required = false)
	private Boolean enabled;

	@ApiModelProperty(value = "mesas disponibles", required = true)
	private List<BoardDto> boardList;

}
