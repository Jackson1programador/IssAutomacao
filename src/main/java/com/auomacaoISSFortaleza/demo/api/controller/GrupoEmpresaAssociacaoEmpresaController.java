package com.auomacaoISSFortaleza.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auomacaoISSFortaleza.demo.domain.service.GrupoEmpresaService;

@RestController
@RequestMapping("/grupo-empresa-associacao")
public class GrupoEmpresaAssociacaoEmpresaController {

	@Autowired
	private GrupoEmpresaService grupoEmpresaService;
	
	
	
	@PutMapping(value="/{idGrupoEmpresa}/empresa/{idEmpresa}")
	@ResponseStatus(value = HttpStatus.OK)
	public void associacao( @PathVariable Long idGrupoEmpresa, @PathVariable Long idEmpresa ) {
		grupoEmpresaService.associarEmpresaAoGrupoEmpresa(idGrupoEmpresa, idEmpresa);
	}
	
	@DeleteMapping(value="/{idGrupoEmpresa}/empresa/{idEmpresa}")
	@ResponseStatus(value = HttpStatus.OK)
	public void desassociacao( @PathVariable Long idGrupoEmpresa, @PathVariable Long idEmpresa ) {
		grupoEmpresaService.desassociarEmpresaAoGrupoEmpresa(idGrupoEmpresa, idEmpresa);
	}
	
}
