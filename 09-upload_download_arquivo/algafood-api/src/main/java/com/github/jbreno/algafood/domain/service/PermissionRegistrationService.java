package com.github.jbreno.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.PermissionNotFoundException;
import com.github.jbreno.algafood.domain.model.Permission;
import com.github.jbreno.algafood.domain.repository.PermissionRepository;

@Service
public class PermissionRegistrationService {
	
	private static final String MSG_PERMISSION_IN_USE 
	= "Permissão de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	public List<Permission> list() {
		return permissionRepository.findAll();
	}
	
	public Permission search(Long id) {
		Optional<Permission> Permission =  permissionRepository.findById(id);
		return Permission.get();
	}
	
	@Transactional	
	public Permission save(Permission permission) {
		return permissionRepository.save(permission);
	
	}
	
	@Transactional
	public void remove (Long id) {
		try {
			permissionRepository.deleteById(id);
			permissionRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new PermissionNotFoundException(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_PERMISSION_IN_USE, id));
		}
	}
	
	public Permission searchOrFail(Long id) {
		return permissionRepository.findById(id)
				.orElseThrow(() -> new PermissionNotFoundException(id));
	}
	
}