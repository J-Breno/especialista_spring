package com.github.jbreno.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface PhotoStorageService {
	void store(NewPhoto newPhoto);
	
	default String genareteNameFile(String nameOriginal) {
		return UUID.randomUUID().toString() + "_" + nameOriginal;
	}
	
	@Getter
	@Builder
	class NewPhoto {
		private String nameFile;
		private InputStream inputStream;
	}
}
