package com.auomacaoISSFortaleza.demo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auomacaoISSFortaleza.demo.api.assembler.GrupoEmpresaDtoAssembler;
import com.auomacaoISSFortaleza.demo.api.assembler.GrupoEmpresaDtoDesassembler;
import com.auomacaoISSFortaleza.demo.api.model.input.EmpresaInputDTO;
import com.auomacaoISSFortaleza.demo.api.model.input.GrupoEmpresaInputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.EmpresaDetalhadoOutputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.GrupoEmpresaDetalhadoOutputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.GrupoEmpresaOutputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.Empresa;
import com.auomacaoISSFortaleza.demo.domain.model.GrupoEmpresa;
import com.auomacaoISSFortaleza.demo.domain.service.GrupoEmpresaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/grupo-empresa")
public class GrupoEmpresaController {

	@Autowired
	private GrupoEmpresaService grupoEmpresaService;
	
	@Autowired
	private GrupoEmpresaDtoAssembler grupoEmpresaDtoAssembler;
	
	@Autowired
	private GrupoEmpresaDtoDesassembler grupoEmpresaDtoDesassembler;
	
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<GrupoEmpresaOutputDTO> list() {
		return grupoEmpresaDtoAssembler.toCollectionModel(grupoEmpresaService.list());
	}
	
	@GetMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public GrupoEmpresaDetalhadoOutputDTO  buscarPorId(@PathVariable Long id) {
		return grupoEmpresaDtoAssembler.toModelForGrupoEmpresaDetalhado(grupoEmpresaService.buscarOuFalhar(id));
	}
	
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public GrupoEmpresaDetalhadoOutputDTO incluir( @RequestBody @Valid GrupoEmpresaInputDTO grupoEmpresaDto) {
		GrupoEmpresa grupoEmpresa = grupoEmpresaDtoDesassembler.toDomainObject(grupoEmpresaDto);
		return grupoEmpresaDtoAssembler.toModelForGrupoEmpresaDetalhado(grupoEmpresaService.salvar(grupoEmpresa));
	}
	
	@PutMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public GrupoEmpresaDetalhadoOutputDTO atualizar( @RequestBody @Valid GrupoEmpresaInputDTO grupoEmpresaDto, @PathVariable Long id) {
		GrupoEmpresa grupoEmpresaASerAtualizada = grupoEmpresaService.buscarOuFalhar(id);
		grupoEmpresaDtoDesassembler.copyToDomainObject(grupoEmpresaDto, grupoEmpresaASerAtualizada);
		return grupoEmpresaDtoAssembler.toModelForGrupoEmpresaDetalhado(grupoEmpresaService.salvar(grupoEmpresaASerAtualizada));
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		grupoEmpresaService.excluir(id);
	}
}
