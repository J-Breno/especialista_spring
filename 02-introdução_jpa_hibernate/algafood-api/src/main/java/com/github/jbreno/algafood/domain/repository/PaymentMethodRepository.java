package com.github.jbreno.algafood.domain.repository;

import java.util.List;

import com.github.jbreno.algafood.domain.model.PaymentMethod;

public interface PaymentMethodRepository {
	List<PaymentMethod> all();
	PaymentMethod search(Long id);
	PaymentMethod save(PaymentMethod paymentMethod);
	void remove(PaymentMethod paymentMethod);
}
