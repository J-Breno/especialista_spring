package com.github.jbreno.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoProductDTO {
	private String fileName;
	private String description;
	private String contentType;
	private Long size;
}
