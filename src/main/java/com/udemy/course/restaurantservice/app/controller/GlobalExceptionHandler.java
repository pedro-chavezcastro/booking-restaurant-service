package com.udemy.course.restaurantservice.app.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.udemy.course.restaurantservice.app.dto.ErrorDto;
import com.udemy.course.restaurantservice.app.exception.RestaurantNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	private ResponseEntity<?> handleAll(Exception e) {

		ErrorDto error = new ErrorDto();
		error.setTimestamp(LocalDateTime.now());
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setMessage(e.getMessage());

		return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
	}
	
	@ExceptionHandler(value = RestaurantNotFoundException.class)
	private ResponseEntity<?> handleRestaurantNotFoundException(RestaurantNotFoundException e){
		
		ErrorDto error = new ErrorDto();
		error.setTimestamp(LocalDateTime.now());
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setMessage(e.getMessage());

		return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = new ArrayList<>();
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			errors.add(fieldError.getField().concat(":").concat(fieldError.getDefaultMessage()));
		}
		for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName().concat(":").concat(error.getDefaultMessage()));
		}
		
		ErrorDto error = new ErrorDto();
		error.setTimestamp(LocalDateTime.now());
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setMessage(errors.toString());

		return super.handleExceptionInternal(e, error, headers, error.getStatus(), request);
	}

}
