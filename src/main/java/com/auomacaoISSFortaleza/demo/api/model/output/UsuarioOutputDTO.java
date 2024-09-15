package com.auomacaoISSFortaleza.demo.api.model.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioOutputDTO {

	private Long id;
	
	private String nome;
	
	private GrupoEmpresaOutputDTOresumido grupoEmpresa;
	
	private GrupoPermissaoOutputDTOresumido grupoPermissao;
	
	//private GrupoUsuario grupoUsuario;
	
	private ClienteOutputDTO cliente;
}
