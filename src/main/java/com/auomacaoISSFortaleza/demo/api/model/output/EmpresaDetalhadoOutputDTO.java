package com.auomacaoISSFortaleza.demo.api.model.output;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.auomacaoISSFortaleza.demo.domain.model.Cliente;
import com.auomacaoISSFortaleza.demo.domain.model.GrupoEmpresa;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaDetalhadoOutputDTO {

	private Long id;
	
	private String nome;
	
	private String cnpj;
	
	private String inscricaoMunicipal;
	
	private String cpfLogin;
	
	private String senhaIssFortaleza;
	
	//private GrupoEmpresa grupoEmpresa;
	
	//private Cliente cliente;
	
	private OffsetDateTime dataCadastro;
	
	private boolean isForAceiteDosServicosTomados;
	private boolean isForEncerrarPeriodo;
	private boolean isForDowloadServiçosTomados;
	private boolean isForDowloadServiçosPrestados;
	private boolean isForGeracaoDaGuia;
	private boolean isForResumoDoSimplesNacional;
	private boolean isForEnviarEmail;
}
