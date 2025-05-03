package com.github.jbreno.algafood.domain.repository.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderFilter {
	
	private Long clientId;
	private Long restaurantId;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime creationDateStart;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime creationDateEnd;
}
