package com.github.jbreno.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

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
}
