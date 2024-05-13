package com.auomacaoISSFortaleza.demo.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	
	private Long id;
	private String nome;
	private String cnpj;
	private List<Usuario> usuarios = new ArrayList<>();
	private List<Empresa> empresas = new ArrayList<>();
	
	
	public void addListEmpresas (Empresa empresa) {
		this.empresas.add(empresa);
	}
	
	public void removeListEmpresas (Empresa empresa) {
		this.empresas.remove(empresa);
	}
	
	public void addListUsuarios (Usuario usuario) {
		this.usuarios.add(usuario);
	}
	
	public void removeListUsuarios (Usuario usuario) {
		this.usuarios.remove(usuario);
	}
	
}
