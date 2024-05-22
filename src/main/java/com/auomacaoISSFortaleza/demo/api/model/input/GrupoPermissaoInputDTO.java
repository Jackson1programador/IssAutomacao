package com.auomacaoISSFortaleza.demo.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoPermissaoInputDTO {

	@NotBlank
	private String nome;
	
}
