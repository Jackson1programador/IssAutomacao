package com.auomacaoISSFortaleza.demo.api.model.output;

import java.util.Set;

import com.auomacaoISSFortaleza.demo.domain.model.Empresa;
import com.auomacaoISSFortaleza.demo.domain.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoEmpresaDetalhadoOutputDTO {

	
	private Long id;
	
	private String nome;
	
	//private Set<Empresa> empresas;
	
	//private Set<Usuario> usuarios;
}
