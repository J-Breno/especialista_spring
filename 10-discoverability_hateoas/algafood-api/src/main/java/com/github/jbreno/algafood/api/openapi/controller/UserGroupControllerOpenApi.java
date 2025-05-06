package com.github.jbreno.algafood.api.openapi.controller;

import java.util.List;

import com.github.jbreno.algafood.api.model.GroupDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Usuários")
public interface UserGroupControllerOpenApi {
	@ApiOperation(value = "Lista grupos de um usuário")
	public List<GroupDTO> list(
			@ApiParam(value = "ID de um usário", example = "1", required = true)  Long id);

	@ApiOperation(value = "disassocia um grupo de um usuário")
	public void disassociate(
			@ApiParam(value = "ID de um usuário", example = "1", required = true)  Long id,
			@ApiParam(value = "ID de grupo de um usuário", example = "1", required = true)  Long groupId);

	@ApiOperation(value = "associa grupo de um usuário")
	public void associate(
			@ApiParam(value = "ID de um usuário", example = "1", required = true)  Long id,
			@ApiParam(value = "ID de grupo de um usuário", example = "1", required = true)  Long groupId);

}
