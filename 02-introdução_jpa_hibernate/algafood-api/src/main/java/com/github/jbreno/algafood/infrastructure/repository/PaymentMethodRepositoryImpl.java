package com.github.jbreno.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.model.PaymentMethod;
import com.github.jbreno.algafood.domain.repository.PaymentMethodRepository;
@Component
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<PaymentMethod> all() {
		return manager.createQuery("from PaymentMethod", PaymentMethod.class)
				.getResultList();
	}
	
	@Override
	public PaymentMethod search(Long id) {
		return manager.find(PaymentMethod.class, id);
	}
	
	@Override
	@Transactional
	public PaymentMethod save(PaymentMethod paymentMethod) {
		return manager.merge(paymentMethod);
	}
	
	@Override
	@Transactional
	public void remove(PaymentMethod paymentMethod) {
		paymentMethod = search(paymentMethod.getId());
		manager.remove(paymentMethod);
	}
}
