package com.udemy.course.restaurantservice.app.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class BoardDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "identificador de la mesa", readOnly = true, required = false)
	private Long id;
	
	@ApiModelProperty(value = "capacidad depersonas", readOnly = true, required = false)
	private Long capacity;
	
	@ApiModelProperty(value = "número de la mesa", readOnly = true, required = false)
	private Long number;

}
