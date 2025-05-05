package com.github.jbreno.algafood.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.jbreno.algafood.api.exceptionhandler.Problem;
import com.github.jbreno.algafood.api.model.KitchenDTO;
import com.github.jbreno.algafood.api.model.input.KitchenInputDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface KitchenControllerOpenApi {
	@ApiOperation("Lista cozinhas")
	public Page<KitchenDTO> list(Pageable pageable) ;
	
	@ApiOperation("Busca uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cozinha inválida", response = Problem.class),
		@ApiResponse(code = 404, message = "cozinha não encontrada", response =  Problem.class)
	})
	public KitchenDTO search(
			@ApiParam(value = "ID de uma cozinha", example = "1") 
			Long id);
	
	@ApiOperation("Cadastra uma cozinha")
	@ApiResponses({
		@ApiResponse(code = 201, message = "cozinha cadastrada"),
	})
	public KitchenDTO add(
			@ApiParam(name = "corpo", value = "Representação de uma nova cozinha")
			KitchenInputDTO kitchenInputDTO) ;
	
	@ApiOperation("Atualiza uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "cozinha atualizada"),
		@ApiResponse(code = 404, message = "cozinha não encontrada", response =  Problem.class)
	})
	public KitchenDTO update(
			@ApiParam(value = "ID de uma cozinha", example = "1")
			Long id,
			@ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados")
			KitchenInputDTO kitchenInputDTO);
	
	@ApiOperation("Deleta uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "cozinha excluída"),
		@ApiResponse(code = 404, message = "cozinha não encontrada", response =  Problem.class)
	})
	public void remove(
			@ApiParam(value = "ID de uma cozinha", example = "1")
					Long id);
}
