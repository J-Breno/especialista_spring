package com.github.jbreno.algafood.api.controller.openapi;

import java.util.List;

import com.github.jbreno.algafood.api.exceptionhandler.Problem;
import com.github.jbreno.algafood.api.model.CityDTO;
import com.github.jbreno.algafood.api.model.input.CityInputDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CityControllerOpenApi {
	@ApiOperation("Lista cidades")
	public List<CityDTO> list() ;
	
	@ApiOperation("Busca um cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response =  Problem.class)
	})
	public CityDTO search(
			@ApiParam(value = "ID de uma cidade", example = "1") 
			Long id);
	
	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada"),
	})
	public CityDTO add(
			@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
			CityInputDTO cityInputDTO) ;
	
	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade atualizada"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response =  Problem.class)
	})
	public CityDTO update(
			@ApiParam(value = "ID de uma cidade", example = "1")
			Long id,
			@ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados")
			CityInputDTO cityInputDTO);
	
	@ApiOperation("Deleta uma cidade")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cidade excluída"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response =  Problem.class)
	})
	public void remove(
			@ApiParam(value = "ID de uma cidade", example = "1")
					Long id);
}
