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

import com.auomacaoISSFortaleza.demo.api.assembler.UsuarioDtoAssembler;
import com.auomacaoISSFortaleza.demo.api.assembler.UsuarioDtoDesassembler;
import com.auomacaoISSFortaleza.demo.api.model.input.UsuarioInputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.UsuarioDetalhadoOutputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.UsuarioOutputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.Usuario;
import com.auomacaoISSFortaleza.demo.domain.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioDtoAssembler usuarioDtoAssembler;
	
	@Autowired
	private UsuarioDtoDesassembler usuarioDtoDesassembler;
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<UsuarioOutputDTO> list() {
		return usuarioDtoAssembler.toCollectionModel(usuarioService.list());
	}
	
	@GetMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public UsuarioDetalhadoOutputDTO  buscarPorId(@PathVariable Long id) {
		return usuarioDtoAssembler.toModelforUsuarioDetalhado(usuarioService.buscarOuFalhar(id));
	}
	
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public UsuarioOutputDTO incluir( @RequestBody @Valid UsuarioInputDTO usuarioDto) {
		Usuario usuario = usuarioDtoDesassembler.toDomainObject(usuarioDto);
		return usuarioDtoAssembler.toModel(usuarioService.salvar(usuario));
	}
	
	@PutMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public UsuarioOutputDTO atualizar( @RequestBody @Valid UsuarioInputDTO usuarioDto, @PathVariable Long id) {
		Usuario usuarioASerAtualizada = usuarioService.buscarOuFalhar(id);
		usuarioDtoDesassembler.copyToDomainObject(usuarioDto, usuarioASerAtualizada);
		return usuarioDtoAssembler.toModel(usuarioService.salvar(usuarioASerAtualizada));
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		usuarioService.excluir(id);
	}
}
