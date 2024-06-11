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

import com.auomacaoISSFortaleza.demo.api.assembler.PermissaoDtoAssembler;
import com.auomacaoISSFortaleza.demo.api.assembler.PermissaoDtoDesassembler;
import com.auomacaoISSFortaleza.demo.api.model.input.PermissaoInputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.PermissaoOutputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.Permissao;
import com.auomacaoISSFortaleza.demo.domain.service.PermissaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

	@Autowired
	private PermissaoService permissaoService;
	
	@Autowired
	private PermissaoDtoAssembler permissaoDtoAsembler;
	
	@Autowired
	private PermissaoDtoDesassembler permissaoDtoDesasembler;
	
	
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public PermissaoOutputDTO incluir( @RequestBody @Valid PermissaoInputDTO permissaoDto) {
		Permissao permissao = permissaoDtoDesasembler.toDomainObject(permissaoDto);
		return permissaoDtoAsembler.toModel(permissaoService.salvar(permissao));
	}
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<PermissaoOutputDTO> listar() {
		return permissaoDtoAsembler.toCollectionModel(permissaoService.list());
	}
	
	
	@GetMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public PermissaoOutputDTO  buscarPorId(@PathVariable Long id) {
		return permissaoDtoAsembler.toModel(permissaoService.buscarOuFalhar(id));
	}
	
	@PutMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public PermissaoOutputDTO atualizar( @RequestBody @Valid PermissaoInputDTO permissaoDto, @PathVariable Long id) {
		Permissao permissaoASerAtualizada = permissaoService.buscarOuFalhar(id);
		permissaoDtoDesasembler.copyToDomainObject(permissaoDto, permissaoASerAtualizada);
		return permissaoDtoAsembler.toModel(permissaoService.salvar(permissaoASerAtualizada));
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		permissaoService.excluir(id);
	}
	
}
