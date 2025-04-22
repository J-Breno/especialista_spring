package com.github.jbreno.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.EntityNotFoundException;


@ControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> treatEntityNotFoundException(EntityNotFoundException e) {
		Problem problem = Problem.builder()
				.dateTime(LocalDateTime.now())
				.message(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problem);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> treatBusinessException(BusinessException e) {
		Problem problem = Problem.builder()
				.dateTime(LocalDateTime.now())
				.message(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(problem);
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> treatHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
		Problem problem = Problem.builder()
				.dateTime(LocalDateTime.now())
				.message("O tipo de mídia não é aceito").build();
		
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
				.body(problem);
	}
}
