package com.github.jbreno.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.api.assembler.PaymentMethodDTOAssembler;
import com.github.jbreno.algafood.api.assembler.PaymentMethodInputDisasembler;
import com.github.jbreno.algafood.api.model.PaymentMethodDTO;
import com.github.jbreno.algafood.api.model.input.PaymentMethodInputDTO;
import com.github.jbreno.algafood.api.openapi.controller.PaymentMethodControllerOpenApi;
import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.RestaurantNotFoundException;
import com.github.jbreno.algafood.domain.model.PaymentMethod;
import com.github.jbreno.algafood.domain.service.PaymentMethodRegistrationService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping(value = "/payments")
public class PaymentMethodController implements PaymentMethodControllerOpenApi{
	
	@Autowired
	private PaymentMethodRegistrationService paymentMethodService;
	
	@Autowired
	private PaymentMethodDTOAssembler paymentMethodDTOAssembler;
	
	@Autowired
	private PaymentMethodInputDisasembler paymentMethodDisassembler;
	
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
				name="campos", paramType = "query", type = "string")
	})
	@GetMapping
	public List<PaymentMethodDTO> list() {
		return paymentMethodDTOAssembler.toCollectionDTO(paymentMethodService.list());
	}

	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
				name="campos", paramType = "query", type = "string")
	})
	@GetMapping("/{id}")
	public PaymentMethodDTO search(@PathVariable Long id) {
		PaymentMethod paymentMethod = paymentMethodService.searchOrFail(id);
		
		return paymentMethodDTOAssembler.toModel(paymentMethod);
	}

	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PaymentMethodDTO add(@RequestBody @Valid PaymentMethodInputDTO paymentMethodInput) {
		try {
			PaymentMethod paymentMethod =  paymentMethodDisassembler.toDomainObject(paymentMethodInput);
			return paymentMethodDTOAssembler.toModel(paymentMethodService.save(paymentMethod));
		}
		catch(RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	public PaymentMethodDTO update(@PathVariable Long id,@RequestBody @Valid PaymentMethodInputDTO paymentMethodInputDTO) {
		try {
			PaymentMethod currentPaymentMethod = paymentMethodService.searchOrFail(id);
			
			paymentMethodDisassembler.copyToDomainObject(paymentMethodInputDTO, currentPaymentMethod);
			
			return paymentMethodDTOAssembler.toModel(paymentMethodService.save(currentPaymentMethod));
		}
		catch(RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {	
		paymentMethodService.remove(id);
	}
}
