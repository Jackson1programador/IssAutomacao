package com.auomacaoISSFortaleza.demo.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tb_cliente")
public class Cliente {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cnpj;
	
	@Transient
	private List<Usuario> usuarios = new ArrayList<>();
	
	@Transient
	private List<Empresa> empresas = new ArrayList<>();

	
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + "]";
	}

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(Long id, @NotBlank String nome, @NotBlank String cnpj) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
	}
	
	



	
	
	
}
