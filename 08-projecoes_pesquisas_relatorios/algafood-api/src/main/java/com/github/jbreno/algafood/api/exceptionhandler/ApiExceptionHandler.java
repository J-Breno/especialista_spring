package com.github.jbreno.algafood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.EntityNotFoundException;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final String MSG_END_USER_ERROR_MESSAGE = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o " +
			"problema persistir, entre em contato com o administrador do sistema.";

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		return handleValidationInternal(ex, status, request, ex.getBindingResult());
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException)rootCause, headers, status, request);
		}
		
		if(rootCause instanceof IgnoredPropertyException) {
			return handleIgnoredPropertyException((IgnoredPropertyException)rootCause, headers, status, request);
		}
		
		if(rootCause instanceof UnrecognizedPropertyException) {
			return handleUnrecognizedPropertyException((UnrecognizedPropertyException)rootCause, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
		String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
				
		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_END_USER_ERROR_MESSAGE).build();
		return handleExceptionInternal(ex,problem, new HttpHeaders(), status, request);
	}	
	
	private ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
		String detail = String.format("A propriedade '%s' não existe no tipo %s",
					path, ex.getReferringClass().getName());
		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_END_USER_ERROR_MESSAGE).build();
		
		return  handleExceptionInternal(ex,problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handleIgnoredPropertyException(IgnoredPropertyException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
		String detail = String.format("A propriedade '%s' foi ignorada no tipo %s",
					path, ex.getReferringClass().getName());
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_END_USER_ERROR_MESSAGE)
				.build();
		
		return  handleExceptionInternal(ex,problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe o valor compatível com o tipo %s",
					path, ex.getValue(), ex.getTargetType().getSimpleName());
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_END_USER_ERROR_MESSAGE)
				.build();
		
		return  handleExceptionInternal(ex,problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> treatEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> treatException(Exception e, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType = ProblemType.SYSTEM_ERROR;
		String detail = String.format(MSG_END_USER_ERROR_MESSAGE);
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleValidationInternal(ex, status, request, ex.getBindingResult());
	}

	private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpStatus status,
			WebRequest request, BindingResult bindingResult) {
		ProblemType problemType = ProblemType.INVALID_DATA;
		String detail = String.format("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
						
		List<Problem.Field> problemFields = bindingResult.getFieldErrors().stream()
			.map(fieldError -> {
				String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
				
				return Problem.Field.builder()
						.name(fieldError.getField())
						.userMessage(message)
						.build();})
			.collect(Collectors.toList());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.fields(problemFields)
				.build();
		return handleExceptionInternal(ex,problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
		String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());
				
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		return handleExceptionInternal(ex,problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> treatMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.INVALID_PARAMETER;
		String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe o valor compatível com o tipo %s",
				e.getParameter().getParameterName(), e.getValue(), e.getRequiredType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_END_USER_ERROR_MESSAGE)
				.build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}
	
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> treatBusinessException(BusinessException e, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ERROR_BUSINESS;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntityInUseException.class)
	public ResponseEntity<?> treatEntityInUseException(EntityInUseException e, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTITY_IN_USE;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
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
