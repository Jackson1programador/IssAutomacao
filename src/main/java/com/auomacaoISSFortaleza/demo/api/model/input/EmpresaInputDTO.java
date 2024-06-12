package com.auomacaoISSFortaleza.demo.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaInputDTO {

	
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
	
	@NotNull
	private boolean isForAceiteDosServicosTomados;
	
	@NotNull
	private boolean isForEncerrarPeriodo;
	
	@NotNull
	private boolean isForDowloadServiçosTomados;
	
	@NotNull
	private boolean isForDowloadServiçosPrestados;
	
	@NotNull
	private boolean isForGeracaoDaGuia;
	
	@NotNull
	private boolean isForResumoDoSimplesNacional;
	
	@NotNull
	private boolean isForEnviarEmail;
}
