package com.github.jbreno.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface PhotoStorageService {
	void store(NewPhoto newPhoto);
	
	void remove(String nameFile);
	
	InputStream recuperar(String nomeArquivo);
	
	default void toReplace(String nameFileOld, NewPhoto newPhoto) {
		this.store(newPhoto);
		
		if(nameFileOld != null) {
			this.remove(nameFileOld);
		}
	}
	
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
