package com.auomacaoISSFortaleza.demo.domain.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tb_grupo_usuario")
public class GrupoUsuario {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@OneToMany(mappedBy = "grupoUsuario", cascade = CascadeType.ALL)
	private Set<Usuario> usuarios = new HashSet<>();

	@Override
	public String toString() {
		return "GrupoUsuario [id=" + id + ", nome=" + nome + "]";
	}

	public GrupoUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
