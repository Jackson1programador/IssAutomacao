package com.auomacaoISSFortaleza.demo.api.model.output;

import java.util.Set;

import com.auomacaoISSFortaleza.demo.domain.model.GrupoPermissao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoOutputDTO {

	private Long id;
	
	private String nome;

	private String descricao;
	
	private Set<GrupoPermissao> grupoPermissoes; 	 
}
