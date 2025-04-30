package com.github.jbreno.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.PaymentMethodDTO;
import com.github.jbreno.algafood.domain.model.PaymentMethod;
@Component
public class PaymentMethodDTOAssembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public PaymentMethodDTO toModel(PaymentMethod paymentMethod) {
		return modelMapper.map(paymentMethod, PaymentMethodDTO.class);
	}
	
	public List<PaymentMethodDTO> toCollectionDTO(List<PaymentMethod> paymentMethods) {
		return paymentMethods.stream()
				.map(paymentMethod -> toModel(paymentMethod))
				.collect(Collectors.toList());
	}
}
