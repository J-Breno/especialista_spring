package com.github.jbreno.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.PaymentMethodNotFoundException;
import com.github.jbreno.algafood.domain.model.PaymentMethod;
import com.github.jbreno.algafood.domain.repository.PaymentMethodRepository;

@Service
public class PaymentMethodRegistrationService {
	
	private static final String MSG_RESTAURANT_IN_USE 
		= "Forma de pagamento de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;
	
	public List<PaymentMethod> list() {
		return paymentMethodRepository.findAll();
	}

	public PaymentMethod search(Long id) {
		return searchOrFail(id);
	}
	
	@Transactional
	public PaymentMethod save(PaymentMethod paymentMethod) {
		return paymentMethodRepository.save(paymentMethod);
	}
	
	public void remove (Long id) {
		try {
			paymentMethodRepository.deleteById(id);
			paymentMethodRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new PaymentMethodNotFoundException(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_RESTAURANT_IN_USE, id));
		}
	}	
	
	public PaymentMethod searchOrFail(Long id) {
		return paymentMethodRepository.findById(id)
				.orElseThrow(() -> new PaymentMethodNotFoundException(id));
	}
}
