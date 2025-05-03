package com.github.jbreno.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.UserNotFoundException;
import com.github.jbreno.algafood.domain.model.Group;
import com.github.jbreno.algafood.domain.model.User;
import com.github.jbreno.algafood.domain.repository.UserRepository;

@Service
public class UserRegistrationService {
	
	private static final String MSG_USER_IN_USE 
		= "Usuário de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroupRegistrationService groupService;
	
	@Autowired
	private EntityManager entityManager;
	
	public List<User> list() {
		return userRepository.findAll();
	}

	public User search(Long id) {
		return searchOrFail(id);
	}
	
	@Transactional
	public User save(User user) {
		entityManager.detach(user);
		
		Optional<User> userExist = userRepository.findByEmail(user.getEmail());
		
		if(userExist.isPresent() && !userExist.get().equals(user)) {
			throw new BusinessException(
					String.format("Já existe usuário cadastrado com o email: %s", user.getEmail()));
		}
		
		return userRepository.save(user);
	}
	
	public void remove (Long id) {
		try {
			userRepository.deleteById(id);
			userRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_USER_IN_USE, id));
		}
	}	
	
	@Transactional
	public void desassociateGroup(Long userId, Long groupId) {
		User user = searchOrFail(userId);
		Group group = groupService.searchOrFail(groupId);
		
		user.removeGroup(group);
	}
	
	@Transactional
	public void associateGroup(Long userId, Long groupId) {
		User user = searchOrFail(userId);
		Group group = groupService.searchOrFail(groupId);
		
		user.addGroup(group);
	}
	
	public User searchOrFail(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
	}
}
