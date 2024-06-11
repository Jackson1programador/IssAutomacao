package com.auomacaoISSFortaleza.demo.api.model.output;

import java.util.List;
import java.util.Set;

import com.auomacaoISSFortaleza.demo.domain.model.Empresa;
import com.auomacaoISSFortaleza.demo.domain.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDetalhadoOutputDTO {

	private Long id;
	
	private String nome;
	
	private String cnpj;
	
	//incluir um DTO Usuario resumido
	//private Set<Usuario> usuarios;

	//incluir um DTO empresa resumido
	//private List<Empresa> empresas;
}
