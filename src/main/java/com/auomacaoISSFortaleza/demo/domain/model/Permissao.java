package com.auomacaoISSFortaleza.demo.domain.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_permissao")
public class Permissao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	 @ManyToMany
	    @JoinTable(
	        name = "tb_grupo_permissao_permissao" //,
	        //joinColumns = @JoinColumn(name = "permissao_id"),
	        //inverseJoinColumns = @JoinColumn(name = "grupo_permissao_id")
	    )
	private Set<GrupoPermissao> grupoPermissoes = new HashSet<>();
	 
	 public void addGrupoPermissoes(GrupoPermissao grupoPermissao) {
		 grupoPermissoes.add(grupoPermissao);
		}
		
		public void removeGrupoPermissoes(GrupoPermissao grupoPermissao) {
			grupoPermissoes.remove(grupoPermissao);
		}
}
