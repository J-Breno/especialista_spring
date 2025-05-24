package com.github.jbreno.algafood.domain.service;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;

public interface PhotoStorageService {
	void store(NewPhoto newPhoto);
	
	@Getter
	@Builder
	class NewPhoto {
		private String nameFile;
		private InputStream inputStream;
	}
}
