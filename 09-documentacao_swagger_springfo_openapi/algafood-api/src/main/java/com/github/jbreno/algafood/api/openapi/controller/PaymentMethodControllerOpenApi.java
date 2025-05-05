package com.github.jbreno.algafood.api.openapi.controller;

import java.util.List;

import com.github.jbreno.algafood.api.exceptionhandler.Problem;
import com.github.jbreno.algafood.api.model.PaymentMethodDTO;
import com.github.jbreno.algafood.api.model.input.PaymentMethodInputDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Formas de pagamento")
public interface PaymentMethodControllerOpenApi {
	@ApiOperation("Lista as formas de pagamento")
	public List<PaymentMethodDTO> list() ;
	
	@ApiOperation("Busca uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da forma de pagamento inválida", response = Problem.class),
		@ApiResponse(code = 404, message = "forma de pagamento não encontrada", response =  Problem.class)
	})
	public PaymentMethodDTO search(
			@ApiParam(value = "ID de uma forma de pagamento", example = "1") 
			Long id);
	
	@ApiOperation("Cadastra uma forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 201, message = "forma de pagamento cadastrada"),
	})
	public PaymentMethodDTO add(
			@ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento", required = true)
			PaymentMethodInputDTO paymentMethodInputDTO) ;
	
	@ApiOperation("Atualiza uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "forma de pagamento atualizada"),
		@ApiResponse(code = 404, message = "forma de pagamento não encontrada", response =  Problem.class)
	})
	public PaymentMethodDTO update(
			@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
			Long id,
			@ApiParam(name = "corpo", value = "Representação de uma forma de pagamento com os novos dados")
			PaymentMethodInputDTO paymentMethodInputDTO);
	
	@ApiOperation("Deleta uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "forma de pagamento excluída"),
		@ApiResponse(code = 404, message = "forma de pagamento não encontrada", response =  Problem.class)
	})
	public void remove(
			@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
					Long id);
}
