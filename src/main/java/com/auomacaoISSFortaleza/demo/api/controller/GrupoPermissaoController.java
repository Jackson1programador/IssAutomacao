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

import com.auomacaoISSFortaleza.demo.api.assembler.GrupoPermissaoDtoAssembler;
import com.auomacaoISSFortaleza.demo.api.assembler.GrupoPermissaoDtoDesassembler;
import com.auomacaoISSFortaleza.demo.api.model.input.GrupoPermissaoInputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.GrupoPermissaoOutputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.GrupoPermissao;
import com.auomacaoISSFortaleza.demo.domain.service.GrupoPermissaoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/grupo-permissoes")
public class GrupoPermissaoController {

	@Autowired
	private GrupoPermissaoService GrupoPermissaoService;
	
	@Autowired
	private GrupoPermissaoDtoAssembler GrupoPermissaoDtoAsembler;
	
	@Autowired
	private GrupoPermissaoDtoDesassembler GrupoPermissaoDtoDesasembler;
	
	
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public GrupoPermissaoOutputDTO incluir( @RequestBody @Valid GrupoPermissaoInputDTO grupoPermissaoDto) {
		GrupoPermissao grupoPermissao = GrupoPermissaoDtoDesasembler.toDomainObject(grupoPermissaoDto);
		return GrupoPermissaoDtoAsembler.toModel(GrupoPermissaoService.salvar(grupoPermissao));
	}
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<GrupoPermissaoOutputDTO> listar() {
		return GrupoPermissaoDtoAsembler.toCollectionModel(GrupoPermissaoService.list());
	}
	
	
	@GetMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public GrupoPermissaoOutputDTO  buscarPorId(@PathVariable Long id) {
		return GrupoPermissaoDtoAsembler.toModel(GrupoPermissaoService.buscarOuFalhar(id));
	}
	
	@PutMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public GrupoPermissaoOutputDTO atualizar( @RequestBody @Valid GrupoPermissaoInputDTO grupoPermissaoDto, @PathVariable Long id) {
		GrupoPermissao GrupopermissaoASerAtualizada = GrupoPermissaoService.buscarOuFalhar(id);
		GrupoPermissaoDtoDesasembler.copyToDomainObject(grupoPermissaoDto, GrupopermissaoASerAtualizada);
		return GrupoPermissaoDtoAsembler.toModel(GrupoPermissaoService.salvar(GrupopermissaoASerAtualizada));
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		GrupoPermissaoService.excluir(id);
	}
	
	
}
