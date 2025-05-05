package com.github.jbreno.algafood.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.jbreno.algafood.api.exceptionhandler.Problem;
import com.github.jbreno.algafood.api.model.OrderDTO;
import com.github.jbreno.algafood.api.model.OrderResumDTO;
import com.github.jbreno.algafood.api.model.input.OrderInputDTO;
import com.github.jbreno.algafood.domain.filter.OrderFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface OrderControllerOpenApi {
	
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
				name="campos", paramType = "query", type = "string")
	})
	@ApiOperation("Lista os pedido")
	public Page<OrderResumDTO> list(OrderFilter orderFilter, Pageable pageable) ;
	
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
				name="campos", paramType = "query", type = "string")
	})
	@ApiOperation("Busca uma pedido por code")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da pedido inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "pedido não encontrado", response =  Problem.class)
	})
	public OrderDTO search(
			@ApiParam(value = "ID de um pedido", example = "81226e3b-522c-48b3-8749-4b0d9d8e6099") 
			String code);
	
	@ApiOperation("Cadastra um pedido")
	@ApiResponses({
		@ApiResponse(code = 201, message = "pedido cadastrada"),
	})
	public OrderDTO add(
			@ApiParam(name = "corpo", value = "Representação de um nova pedido")
			OrderInputDTO orderInputDTO) ;
	
	@ApiOperation("Atualiza um pedido por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "pedido atualizada"),
		@ApiResponse(code = 404, message = "pedido não encontrada", response =  Problem.class)
	})
	public OrderDTO update(
			@ApiParam(value = "ID de um pedido", example = "1")
			Long id,
			@ApiParam(name = "corpo", value = "Representação de um pedido com os novos dados")
			OrderInputDTO orderInputDTO);
	
	@ApiOperation("Deleta um pedido por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "pedido excluída"),
		@ApiResponse(code = 404, message = "pedido não encontrada", response =  Problem.class)
	})
	public void remove(
			@ApiParam(value = "ID de um pedido", example = "1")
					Long id);
}
