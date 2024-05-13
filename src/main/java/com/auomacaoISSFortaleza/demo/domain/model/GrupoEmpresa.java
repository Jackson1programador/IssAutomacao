package com.auomacaoISSFortaleza.demo.domain.model;

import java.util.ArrayList;
import java.util.List;

public class GrupoEmpresa {

	private Long id;
	private List<Empresa> empresas = new ArrayList<>();
	
	public void addEmpresas(Empresa empresa) {
		empresas.add(empresa);
	}
	
	public void removeEmpresas(Empresa empresa) {
		empresas.remove(empresa);
	}
}
