package com.auomacaoISSFortaleza.demo.domain.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tb_grupo_permissao")
public class GrupoPermissao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "grupoPermissoes")
	private Set<Permissao> permissoes = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "grupoPermissao")
	private Set<Usuario> usuarios = new HashSet<>();
	
	

	@Override
	public String toString() {
		return "GrupoPermissao [id=" + id + ", nome=" + nome + ", permissoes=" + "NÃO POSSO INCLUIR INFORMACAO NESSE CAMPO, SE NÃO ELE ENTRA EM UM LUP INFINITO" + "]";
	}

	public GrupoPermissao() {
		super();
	}
	
	
	
	
	
	
}
