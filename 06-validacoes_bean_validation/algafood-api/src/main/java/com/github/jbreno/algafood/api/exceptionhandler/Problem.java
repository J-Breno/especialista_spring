package com.github.jbreno.algafood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Problem {

	private LocalDateTime dateTime;
	private Integer status;
	private String detail;
	private String type;
	private String title;
	
	private String userMessage;
	
	private List<Field> fields;
	
	@Getter
	@Builder
	public static class Field {
		private String name;
		private String userMessage;
	}
}
