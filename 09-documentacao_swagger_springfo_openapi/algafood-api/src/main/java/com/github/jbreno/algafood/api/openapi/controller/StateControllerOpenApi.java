package com.github.jbreno.algafood.api.openapi.controller;

import java.util.List;

import com.github.jbreno.algafood.api.exceptionhandler.Problem;
import com.github.jbreno.algafood.api.model.StateDTO;
import com.github.jbreno.algafood.api.model.input.StateInputDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface StateControllerOpenApi {
	@ApiOperation("Lista estatod")
	public List<StateDTO> list() ;
	
	@ApiOperation("Busca um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do estado inválida", response = Problem.class),
		@ApiResponse(code = 404, message = "estado não encontrado", response =  Problem.class)
	})
	public StateDTO search(
			@ApiParam(value = "ID de um estado", example = "1") 
			Long id);
	
	@ApiOperation("Cadastra um estado")
	@ApiResponses({
		@ApiResponse(code = 201, message = "estado cadastrada"),
	})
	public StateDTO add(
			@ApiParam(name = "corpo", value = "Representação de um nova estado", required = true)
			StateInputDTO StateInputDTO) ;
	
	@ApiOperation("Atualiza um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "estado atualizado"),
		@ApiResponse(code = 404, message = "estado não encontrado", response =  Problem.class)
	})
	public StateDTO update(
			@ApiParam(value = "ID de uma estado", example = "1", required = true)
			Long id,
			@ApiParam(name = "corpo", value = "Representação de uma estado com os novos dados")
			StateInputDTO StateInputDTO);
	
	@ApiOperation("Deleta um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "estado excluído"),
		@ApiResponse(code = 404, message = "estado não encontrado", response =  Problem.class)
	})
	public void remove(
			@ApiParam(value = "ID de uma estado", example = "1", required = true)
					Long id);
}
