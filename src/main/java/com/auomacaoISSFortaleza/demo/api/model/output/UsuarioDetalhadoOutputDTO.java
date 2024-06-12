package com.auomacaoISSFortaleza.demo.api.model.output;

import java.time.OffsetDateTime;

import com.auomacaoISSFortaleza.demo.domain.model.Cliente;
import com.auomacaoISSFortaleza.demo.domain.model.GrupoEmpresa;
import com.auomacaoISSFortaleza.demo.domain.model.GrupoPermissao;
import com.auomacaoISSFortaleza.demo.domain.model.GrupoUsuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDetalhadoOutputDTO {

	private Long id;
	
	private String nome;
	
	//private GrupoEmpresa grupoEmpresa;
	
	//private GrupoPermissao grupoPermissao;
	
	//private GrupoUsuario grupoUsuario;
	
	private ClienteOutputDTO cliente;
	
	private OffsetDateTime dataCadastro;
}
