package com.github.jbreno.algafood.api.openapi.controller;

import java.util.List;

import com.github.jbreno.algafood.api.exceptionhandler.Problem;
import com.github.jbreno.algafood.api.model.UserDTO;
import com.github.jbreno.algafood.api.model.input.PasswordInputDTO;
import com.github.jbreno.algafood.api.model.input.UserWithPasswordInputDTO;
import com.github.jbreno.algafood.api.model.input.UserWithoutInputDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuários")
public interface UserControllerOpenApi {
	@ApiOperation("Lista usuários")
	public List<UserDTO> list() ;
	
	@ApiOperation("Busca um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response =  Problem.class)
	})
	public UserDTO search(
			@ApiParam(value = "ID de um usuário", example = "1") 
			Long id);
	
	@ApiOperation("Cadastra um usuário")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Usuário cadastrado"),
	})
	public UserDTO add(
			@ApiParam(name = "corpo", value = "Representação de um usuário com senha", required = true)
			UserWithPasswordInputDTO userInput) ;
	
	@ApiOperation("Atualiza um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Usuário atualizado"),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response =  Problem.class)
	})
	public UserDTO update(
			@ApiParam(value = "ID de um usuário", example = "1", required = true)
			Long id,
			@ApiParam(name = "corpo", value = "Representação de um usuário sem senha", required = true)
			UserWithoutInputDTO userInputDTO);
	
	@ApiOperation("Deleta um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Usuário excluído"),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response =  Problem.class)
	})
	public void remove(
			@ApiParam(value = "ID de um cidade", example = "1", required = true)
					Long id);
	
	@ApiOperation("Atualiza senha de um usuário")
	public UserWithPasswordInputDTO updatePassword(
				@ApiParam(value = "ID de uma usuário", example = "1", required = true)
				Long id,
				@ApiParam(name = "corpo", value = "Representação da senha antiga e senha nova do usuário", required = true)
				PasswordInputDTO passwordInputDTO);
}
