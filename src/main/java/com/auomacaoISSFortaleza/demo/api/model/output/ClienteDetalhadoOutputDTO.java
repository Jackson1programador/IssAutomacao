package com.auomacaoISSFortaleza.demo.api.model.output;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDetalhadoOutputDTO {

	private Long id;
	
	private String nome;
	
	private String cnpj;
	
	//incluir um DTO Usuario resumido
	private Set<UsuarioOutputDTOresumido> usuariosDoCliente;

	//incluir um DTO empresa resumido
	//private List<Empresa> empresas;
}
