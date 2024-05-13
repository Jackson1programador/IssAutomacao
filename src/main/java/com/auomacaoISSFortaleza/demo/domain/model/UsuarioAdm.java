package com.auomacaoISSFortaleza.demo.domain.model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioAdm extends Usuario{

	private List<Usuario> usuariosSupervisionados = new ArrayList<>();
	
	public void addUsuariosSupervisionados(Usuario usuario) {
		usuariosSupervisionados.add(usuario);
	}
	
	public void removeUsuariosSupervisionados(Usuario usuario) {
		usuariosSupervisionados.remove(usuario);
	}
}
