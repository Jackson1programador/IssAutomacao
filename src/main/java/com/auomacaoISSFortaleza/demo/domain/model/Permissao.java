package com.auomacaoISSFortaleza.demo.domain.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name = "tb_permissao")
public class Permissao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	 
	 @ManyToMany
	    @JoinTable(
	        name = "tb_grupo_permissao_permissao", 
	        joinColumns = {
	        		@JoinColumn(name = "id_permissao"),
	        		
	        }, inverseJoinColumns = {
	        		@JoinColumn(name = "id_grupo_permissao")
	        })
	private Set<GrupoPermissao> grupoPermissoes = new HashSet<>();
	 

		@Override
		public String toString() {
			return "Permissao [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", grupoPermissoes="
					+ "NÃO POSSO INCLUIR INFORMACAO NESSE CAMPO, SE NÃO ELE ENTRA EM UM LUP INFINITO" + "]";
		}


		public Permissao() {
			super();
		}
		
		
}
