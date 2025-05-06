package com.github.jbreno.algafood.api.openapi.controller;

import java.util.List;

import com.github.jbreno.algafood.api.model.PermissionDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Grupos")
public interface GroupPermissionControllerOpenApi {
	@ApiOperation("Lista permissões de grupos")
	public List<PermissionDTO> list(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long id) ;
	
	@ApiOperation(value = "disassocia permissão de um grupo")
	public void disassociate(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)  Long id,
			@ApiParam(value = "ID de permssão de um restaurante", example = "1", required = true)  Long permissionId);

	@ApiOperation(value = "associa permissão de um restaurante")
	public void associate(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)  Long id,
			@ApiParam(value = "ID de permissão de um restaurante", example = "1", required = true)  Long permissionId);

}
