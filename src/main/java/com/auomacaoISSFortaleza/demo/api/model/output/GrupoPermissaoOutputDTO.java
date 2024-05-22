package com.auomacaoISSFortaleza.demo.api.model.output;

import java.util.Set;

import com.auomacaoISSFortaleza.demo.domain.model.Permissao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoPermissaoOutputDTO {

	private Long id;
	
	private String nome;
	
	private Set<Permissao> permissoes;
}
