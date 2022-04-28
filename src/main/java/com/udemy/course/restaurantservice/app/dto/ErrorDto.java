package com.udemy.course.restaurantservice.app.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModelProperty;

public class ErrorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "marca de tiempo", position = 2)
	private LocalDateTime timestamp;
	
	@ApiModelProperty(value = "codigo de respuesta", position = 2)
	private int code;
	
	@ApiModelProperty(value = "estado de la respuesta", position = 2)
	private HttpStatus status;
	
	@ApiModelProperty(value = "mensaje dela respuesta", position = 2)
	private String message;

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
