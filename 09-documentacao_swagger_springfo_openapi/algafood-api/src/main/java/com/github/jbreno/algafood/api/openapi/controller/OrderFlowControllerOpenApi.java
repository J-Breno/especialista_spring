package com.github.jbreno.algafood.api.openapi.controller;

import org.springframework.web.bind.annotation.PathVariable;

import com.github.jbreno.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface OrderFlowControllerOpenApi{
	
	@ApiOperation("Confirma um pedido por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "pedido confirmado"),
		@ApiResponse(code = 404, message = "pedido não encontrado", response =  Problem.class)
	})
	public void confirm(
			@ApiParam(value = "Código de um pedido", example = "81226e3b-522c-48b3-8749-4b0d9d8e6099") 
			@PathVariable String code) ;

	@ApiOperation("Cancela um pedido por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "pedido cancelado"),
		@ApiResponse(code = 404, message = "pedido não encontrado", response =  Problem.class)
	})
	public void caceled(
			@ApiParam(value = "Código de um pedido", example = "81226e3b-522c-48b3-8749-4b0d9d8e6099")
			@PathVariable String code) ;
	
	@ApiOperation("Entrega um pedido por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "pedido entregue"),
		@ApiResponse(code = 404, message = "pedido não encontrado", response =  Problem.class)
	})
	public void delivered(
			@ApiParam(value = "Código de um pedido", example = "81226e3b-522c-48b3-8749-4b0d9d8e6099")
			@PathVariable String code) ;
}
