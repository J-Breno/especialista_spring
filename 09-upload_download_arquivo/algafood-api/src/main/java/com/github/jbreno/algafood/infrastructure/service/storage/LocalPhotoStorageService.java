package com.github.jbreno.algafood.infrastructure.service.storage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.github.jbreno.algafood.domain.service.PhotoStorageService;


@Service
public class LocalPhotoStorageService implements PhotoStorageService{
	
	@Value("${algafood.storage.local.directory-photos}")
	private Path directoryPhotos;

	@Override
	public void store(NewPhoto newPhoto) {	
		try {
			Path filePath = getFilePath(newPhoto.getNameFile());
			FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(filePath));

		} catch (Exception e) {
			throw new StorageException("Não foi possível armazenar arquivo.", e);
		}
	}
	
	@Override
	public void remove(String nameFile) {
		try {
			Path filePath = getFilePath(nameFile);
			Files.deleteIfExists(filePath);
		}
		catch (Exception e) {
			throw new StorageException("Não foi possível excluir arquivo.", e);
		}
	}
	
	@Override
	public InputStream recover(String nomeArquivo) {
	    try {
	        Path arquivoPath = getFilePath(nomeArquivo);

	        return Files.newInputStream(arquivoPath);
	    } catch (Exception e) {
	        throw new StorageException("Não foi possível recuperar arquivo.", e);
	    }
	}  
	
	private Path getFilePath(String nameFile) {
		return directoryPhotos.resolve(Path.of(nameFile));
	}
	
}
