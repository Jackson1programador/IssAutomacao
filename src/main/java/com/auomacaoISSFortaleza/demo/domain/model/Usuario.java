package com.auomacaoISSFortaleza.demo.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//@Data
//@Entity
//@Table(name = "tb_usuario")
public class Usuario {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@NotBlank
	private String nome;
	
	//@NotBlank
	private String email;
	
	//@NotBlank
	private String senha;
	
	//só depois que eu concluir a entidade empresa
	private GrupoEmpresa grupoEmpresa;
	
	
	private GrupoPermissao grupoPermissao;
	
	public void alteraSenha (String novaSenha) {
		
	}
}
