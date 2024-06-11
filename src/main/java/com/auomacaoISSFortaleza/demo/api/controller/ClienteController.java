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

import com.auomacaoISSFortaleza.demo.api.assembler.ClienteDtoAssembler;
import com.auomacaoISSFortaleza.demo.api.assembler.ClienteDtoDesassembler;
import com.auomacaoISSFortaleza.demo.api.model.input.ClienteInputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.ClienteDetalhadoOutputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.ClienteOutputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.Cliente;
import com.auomacaoISSFortaleza.demo.domain.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteDtoAssembler clienteDtoAssembler;
	
	@Autowired
	private ClienteDtoDesassembler clienteDtoDesassembler;
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<ClienteOutputDTO> list() {
		return clienteDtoAssembler.toCollectionModel(clienteService.list());
	}
	
	@GetMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ClienteDetalhadoOutputDTO  buscarPorId(@PathVariable Long id) {
		return clienteDtoAssembler.toModelForClienteDetalhado(clienteService.buscarOuFalhar(id));
	}
	
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public ClienteOutputDTO incluir( @RequestBody @Valid ClienteInputDTO clienteDto) {
		Cliente cliente = clienteDtoDesassembler.toDomainObject(clienteDto);
		return clienteDtoAssembler.toModel(clienteService.salvar(cliente));
	}
	
	@PutMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ClienteOutputDTO atualizar( @RequestBody @Valid ClienteInputDTO clienteDto, @PathVariable Long id) {
		Cliente clienteASerAtualizada = clienteService.buscarOuFalhar(id);
		clienteDtoDesassembler.copyToDomainObject(clienteDto, clienteASerAtualizada);
		return clienteDtoAssembler.toModel(clienteService.salvar(clienteASerAtualizada));
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		clienteService.excluir(id);
	}
}
