package com.github.jbreno.algafood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@Getter
@Builder
public class Problem {
	@ApiModelProperty(example = "2019-10-30T00:00:00Z", position = 5)
	private LocalDateTime dateTime;
	@ApiModelProperty(example = "400", position = 1)
	private Integer status;
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 20)
	private String detail;
	@ApiModelProperty(example = "https://algafood.com.br/invalid_data", position = 10)
	private String type;
	@ApiModelProperty(example = "Dados inválidos", position = 15)
	private String title;
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 25)
	private String userMessage;
	@ApiModelProperty(value = "Lista de bjetos ou campos que geram o erro(opcional)", position = 30)
	private List<Field> fields;
	
	@Getter
	@Builder
	@ApiModel("ObjetoProblema")
	public static class Field {
		@ApiModelProperty(example = "preco")
		private String name;
		@ApiModelProperty(example = "O preço é obrigatório")
		private String userMessage;
	}
}
