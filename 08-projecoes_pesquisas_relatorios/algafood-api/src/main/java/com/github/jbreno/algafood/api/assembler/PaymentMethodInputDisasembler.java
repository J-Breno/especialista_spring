package com.github.jbreno.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.input.PaymentMethodInputDTO;
import com.github.jbreno.algafood.domain.model.PaymentMethod;

@Component
public class PaymentMethodInputDisasembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PaymentMethod toDomainObject(PaymentMethodInputDTO paymentMethodInputDTO) {
		return modelMapper.map(paymentMethodInputDTO, PaymentMethod.class);
	}
	
	public void copyToDomainObject(PaymentMethodInputDTO paymentMethodInputDTO, PaymentMethod paymentMethod) {
		modelMapper.map(paymentMethodInputDTO, paymentMethod);
	}
	
}
