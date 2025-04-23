package com.github.jbreno.algafood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.EntityNotFoundException;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException)rootCause, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
		String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
				
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(ex,problem, new HttpHeaders(), status, request);
	}
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe o valor compatível com o tipo %s",
					path, ex.getValue(), ex.getTargetType().getSimpleName());
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return  handleExceptionInternal(ex,problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> treatEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTITY_NOT_FOUND;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> treatBusinessException(BusinessException e, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ERROR_BUSINESS;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntityInUseException.class)
	public ResponseEntity<?> treatEntityInUseException(EntityInUseException e, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTITY_IN_USE;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if(body == null) {
			body = Problem.builder()
					.dateTime(LocalDateTime.now())
					.detail(status.getReasonPhrase())
					.build();
		} else if(body instanceof String) {
			body = Problem.builder()
					.dateTime(LocalDateTime.now())
					.detail((String) body)
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
	    return Problem.builder()
	            .status(status.value())
	            .type(problemType.getUri())
	            .title(problemType.getTitle())
	            .detail(detail)
	            .dateTime(LocalDateTime.now());
	}
}
