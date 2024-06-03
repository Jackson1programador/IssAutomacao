package com.auomacaoISSFortaleza.demo.domain.model;

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
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;
	
	@ManyToOne
    @JoinColumn(name = "grupo_empresa_id")
	private GrupoEmpresa grupoEmpresa;
	
	@ManyToOne
    @JoinColumn(name = "grupo_permissoes_id")
	private GrupoPermissao grupoPermissao;
	
	@ManyToOne
    @JoinColumn(name = "grupo_usuarios_id")
	private GrupoUsuario grupoUsuario;
	
	
	
	
	
	public void alteraSenha (String novaSenha) {
		
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
	}

	public Usuario() {
		super();
		// TODO Auto-generated constructor stu
	}

	public Usuario(Long id, @NotBlank String nome, @NotBlank String email, @NotBlank String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
}
