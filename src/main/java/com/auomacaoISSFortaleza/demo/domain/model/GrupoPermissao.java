package com.auomacaoISSFortaleza.demo.domain.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_grupo_permissao")
public class GrupoPermissao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@ManyToMany(mappedBy = "grupoPermissoes")
	private Set<Permissao> permissoes = new HashSet<>();
	
	public void addPermissoes(Permissao permissao) {
		permissoes.add(permissao);
	}
	
	public void removePermissoes(Permissao permissao) {
		permissoes.remove(permissao);
	}
}
