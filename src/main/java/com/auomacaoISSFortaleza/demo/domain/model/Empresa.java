package com.auomacaoISSFortaleza.demo.domain.model;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tb_empresa")
public class Empresa {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cnpj;
	
	@NotBlank
	private String inscricaoMunicipal;
	
	@NotBlank
	private String cpfLogin;
	
	@NotBlank
	private String senhaIssFortaleza;
	
	@ManyToOne
    @JoinColumn(name = "grupo_empresa_id")
	private GrupoEmpresa grupoEmpresa;
	
	@ManyToOne
    @JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	private boolean isForAceiteDosServicosTomados;
	private boolean isForEncerrarPeriodo;
	private boolean isForDowloadServiçosTomados;
	private boolean isForDowloadServiçosPrestados;
	private boolean isForGeracaoDaGuia;
	private boolean isForResumoDoSimplesNacional;
	private boolean isForEnviarEmail;
	
	
	public void aceiteDosServicosTomados() {
		
	}
	
	public void encerrarPeriodo() {
			
	}
	
	public void dowloadServiçosTomados() {
		
	}
	
	public void dowloadServiçosPrestados() {
		
	}
	
	public void geracaoDaGuia() {
		
	}
	
	public void resumoDoSimplesNacional() {
		
	}

	public void enviarEmail() {
		
	}
	
	public void logarISSFortaleza() {
		
	}

	public Empresa() {
		super();
		// TODO Auto-generated constructor stub
	}


}
