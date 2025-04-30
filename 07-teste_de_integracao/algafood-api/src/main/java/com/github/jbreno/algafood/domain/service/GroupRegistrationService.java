package com.github.jbreno.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.GroupNotFoundException;
import com.github.jbreno.algafood.domain.model.Group;
import com.github.jbreno.algafood.domain.repository.GroupRepository;

@Service
public class GroupRegistrationService {
	
	private static final String MSG_GROUP_IN_USE 
		= "Grupo de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private GroupRepository groupRepository;
	
	public List<Group> list() {
		return groupRepository.findAll();
	}

	public Group search(Long id) {
		return searchOrFail(id);
	}
	
	@Transactional
	public Group save(Group group) {
		return groupRepository.save(group);
	}
	
	public void remove (Long id) {
		try {
			groupRepository.deleteById(id);
			groupRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new GroupNotFoundException(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_GROUP_IN_USE, id));
		}
	}	
	
	public Group searchOrFail(Long id) {
		return groupRepository.findById(id)
				.orElseThrow(() -> new GroupNotFoundException(id));
	}
}
