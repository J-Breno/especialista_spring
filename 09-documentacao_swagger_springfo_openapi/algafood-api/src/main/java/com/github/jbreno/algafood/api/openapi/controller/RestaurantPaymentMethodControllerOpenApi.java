package com.github.jbreno.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.github.jbreno.algafood.api.model.PaymentMethodDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Restaurantes")
public interface RestaurantPaymentMethodControllerOpenApi {

	@ApiOperation(value = "Lista Forma de pagamento de um restaurante")
	public List<PaymentMethodDTO> list(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true) @PathVariable Long id);

	@ApiOperation(value = "disassocia Forma de pagamento de um restaurante")
	public void disassociate(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true) @PathVariable Long id,
			@ApiParam(value = "ID de forma de pagamento de um restaurante", example = "1", required = true) @PathVariable Long paymentMethodId);

	@ApiOperation(value = "associa Forma de pagamento de um restaurante")
	public void associate(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true) @PathVariable Long id,
			@ApiParam(value = "ID de forma de pagamento de um restaurante", example = "1", required = true) @PathVariable Long paymentMethodId);
}
