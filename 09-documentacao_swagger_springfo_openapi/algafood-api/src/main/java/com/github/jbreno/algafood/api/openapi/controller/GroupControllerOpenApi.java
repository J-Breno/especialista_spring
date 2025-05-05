package com.github.jbreno.algafood.api.openapi.controller;

import java.util.List;

import com.github.jbreno.algafood.api.exceptionhandler.Problem;
import com.github.jbreno.algafood.api.model.GroupDTO;
import com.github.jbreno.algafood.api.model.input.GroupInputDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GroupControllerOpenApi {
	@ApiOperation("Lista grupos")
	public List<GroupDTO> list() ;
	
	@ApiOperation("Busca um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "grupo não encontrado", response =  Problem.class)
	})
	public GroupDTO search(
			@ApiParam(value = "ID de uma grupo", example = "1") 
			Long id);
	
	@ApiOperation("Cadastra um grupo")
	@ApiResponses({
		@ApiResponse(code = 201, message = "grupo cadastrado"),
	})
	public GroupDTO add(
			@ApiParam(name = "corpo", value = "Representação de uma nova grupo", required = true)
			GroupInputDTO groupInputDTO) ;
	
	@ApiOperation("Atualiza um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "grupo atualizado"),
		@ApiResponse(code = 404, message = "grupo não encontrado", response =  Problem.class)
	})
	public GroupDTO update(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long id,
			@ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados")
			GroupInputDTO groupInputDTO);
	
	@ApiOperation("Deleta um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "grupo excluído"),
		@ApiResponse(code = 404, message = "grupo não encontrado", response =  Problem.class)
	})
	public void remove(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
					Long id);

}
