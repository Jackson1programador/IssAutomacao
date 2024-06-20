package com.auomacaoISSFortaleza.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auomacaoISSFortaleza.demo.domain.service.GrupoPermissaoService;

@RestController
@RequestMapping("/grupo-permissao-associacao")
public class GrupoPermissaoAssociacaoPermissaoController {

	@Autowired
	private GrupoPermissaoService grupoPermissaoService;
	
	
	
	@PutMapping(value="/{idGrupoPermissao}/permissao/{idPermissao}")
	@ResponseStatus(value = HttpStatus.OK)
	public void associacao( @PathVariable Long idGrupoPermissao, @PathVariable Long idPermissao ) {
		grupoPermissaoService.associarPermissaoAoGrupoPermissao(idGrupoPermissao, idPermissao);
	}
	
	@DeleteMapping(value="/{idGrupoPermissao}/permissao/{idPermissao}")
	@ResponseStatus(value = HttpStatus.OK)
	public void desassociacao( @PathVariable Long idGrupoPermissao, @PathVariable Long idPermissao ) {
		grupoPermissaoService.desassociarPermissaoAoGrupoPermissao(idGrupoPermissao, idPermissao);
	}
	
}
