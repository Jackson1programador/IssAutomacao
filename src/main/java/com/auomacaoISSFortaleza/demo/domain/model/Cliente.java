package com.auomacaoISSFortaleza.demo.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Set<Usuario> usuarios = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Empresa> empresas = new ArrayList<>();
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;

	
	
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
