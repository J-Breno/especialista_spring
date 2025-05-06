package com.github.jbreno.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.github.jbreno.algafood.api.model.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Restaurantes")
public interface RestaurantUserControllerOpenApi {

	@ApiOperation(value = "Lista usuários de um restaurante")
	public List<UserDTO> list(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)  Long id);

	@ApiOperation(value = "disassocia usuário de um restaurante")
	public void disassociate(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)  Long id,
			@ApiParam(value = "ID de usuário de um restaurante", example = "1", required = true)  Long userId);

	@ApiOperation(value = "associa usuário de um restaurante")
	public void associate(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)  Long id,
			@ApiParam(value = "ID de usuário de um restaurante", example = "1", required = true)  Long userId);
}
