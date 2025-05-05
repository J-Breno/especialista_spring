package com.github.jbreno.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.jbreno.algafood.api.exceptionhandler.Problem;
import com.github.jbreno.algafood.api.model.RestaurantDTO;
import com.github.jbreno.algafood.api.model.input.RestaurantInputDTO;
import com.github.jbreno.algafood.api.openapi.model.RestaurantsBasicDtoOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestaurantControllerOpenApi {
	@ApiOperation(value = "Lista retaurantes", response = RestaurantsBasicDtoOpenApi.class)
	@ApiImplicitParams({
			@ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "name-only", name = "projecao", paramType = "query", type = "string") })
	public List<RestaurantDTO> list();

	@ApiOperation(value = "Lista retaurantes", hidden = true)
	public default List<RestaurantDTO> listNameOnly() {
		return list();
	}

	@ApiOperation("Busca uma restaurante por ID")
	@ApiResponses({ @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
			@ApiResponse(code = 404, message = "restaurante não encontrado", response = Problem.class) })
	public RestaurantDTO search(@ApiParam(value = "ID de um restaurante", example = "1") Long id);

	@ApiOperation("Cadastra uma restaurante")
	@ApiResponses({ @ApiResponse(code = 201, message = "restaurante cadastrado"), })
	public RestaurantDTO add(
			@ApiParam(name = "corpo", value = "Representação de um nova restaurante", required = true) RestaurantInputDTO restaurantInputDTO);

	@ApiOperation("Atualiza um restaurante por ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "restaurante atualizado"),
			@ApiResponse(code = 404, message = "restaurante não encontrado", response = Problem.class) })
	public RestaurantDTO update(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long id,
			@ApiParam(name = "corpo", value = "Representação de um restaurante com os novos dados") RestaurantInputDTO restaurantInputDTO);

	@ApiOperation("Deleta um restaurante por ID")
	@ApiResponses({ @ApiResponse(code = 204, message = "restaurante excluída"),
			@ApiResponse(code = 404, message = "restaurante não encontrado", response = Problem.class) })
	public void remove(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long id);

	@ApiOperation("Ativa um restaurante por ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "restaurante ativado"),
			@ApiResponse(code = 404, message = "restaurante não encontrado", response = Problem.class) })
	public void active(@ApiParam(value = "ID de um restaurante", example = "1", required = true) @PathVariable Long id);

	@ApiOperation("Inativa um restaurante por ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "restaurante inativado"),
			@ApiResponse(code = 404, message = "restaurante não encontrado", response = Problem.class) })
	public void inactivate(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true) @PathVariable Long id);

	@ApiOperation("Ativa uma lista restaurante por ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "restaurante inativado"),
			@ApiResponse(code = 404, message = "restaurante não encontrado", response = Problem.class) })
	public void activeMultiple(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true) @RequestBody List<Long> ids);

	@ApiOperation("Inativa uma lista restaurante por ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "restaurante inativado"),
			@ApiResponse(code = 404, message = "restaurante não encontrado", response = Problem.class) })
	public void desactiveMultiple(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true) @RequestBody List<Long> ids);

	@ApiOperation("Abre um restaurante por ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "restaurante aberto"),
			@ApiResponse(code = 404, message = "restaurante não encontrado", response = Problem.class) })
	public void open(@ApiParam(value = "ID de um restaurante", example = "1", required = true) @PathVariable Long id);

	@ApiOperation("Fecha um restaurante por ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "restaurante fechado"),
			@ApiResponse(code = 404, message = "restaurante não encontrado", response = Problem.class) })
	public void closing(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true) @PathVariable Long id);
}
