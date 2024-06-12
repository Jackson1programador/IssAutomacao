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

import com.auomacaoISSFortaleza.demo.api.assembler.EmpresaDtoAssembler;
import com.auomacaoISSFortaleza.demo.api.assembler.EmpresaDtoDesassembler;
import com.auomacaoISSFortaleza.demo.api.model.input.EmpresaInputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.EmpresaDetalhadoOutputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.EmpresaOutputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.Empresa;
import com.auomacaoISSFortaleza.demo.domain.service.EmpresaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private EmpresaDtoAssembler empresaDtoAssembler;
	
	@Autowired
	private EmpresaDtoDesassembler empresaDtoDesassembler;
	
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<EmpresaOutputDTO> list() {
		return empresaDtoAssembler.toCollectionModel(empresaService.list());
	}
	
	@GetMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public EmpresaDetalhadoOutputDTO  buscarPorId(@PathVariable Long id) {
		return empresaDtoAssembler.toModelforEmpresaDetalhado(empresaService.buscarOuFalhar(id));
	}
	
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public EmpresaDetalhadoOutputDTO incluir( @RequestBody @Valid EmpresaInputDTO empresaDto) {
		Empresa empresa = empresaDtoDesassembler.toDomainObject(empresaDto);
		return empresaDtoAssembler.toModelforEmpresaDetalhado(empresaService.salvar(empresa));
	}
	
	@PutMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public EmpresaDetalhadoOutputDTO atualizar( @RequestBody @Valid EmpresaInputDTO empresaDto, @PathVariable Long id) {
		Empresa empresaASerAtualizada = empresaService.buscarOuFalhar(id);
		empresaDtoDesassembler.copyToDomainObject(empresaDto, empresaASerAtualizada);
		return empresaDtoAssembler.toModelforEmpresaDetalhado(empresaService.salvar(empresaASerAtualizada));
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		empresaService.excluir(id);
	}
}
