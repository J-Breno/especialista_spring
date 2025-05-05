package com.github.jbreno.algafood.core.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("Pageable")
public class PageableDtoOpenApi {
	
	@ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
	private int page;
	@ApiModelProperty(example = "10", value = "Quantidade de elementos por páginas")
	private int size;
	@ApiModelProperty(example = "nome,asc", value = "Nome da propriedade para ordenção")
	private List<String> sort;
}
