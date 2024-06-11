package com.auomacaoISSFortaleza.demo.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auomacaoISSFortaleza.demo.api.model.output.ClienteDetalhadoOutputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.ClienteOutputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.Cliente;


@Component
public class ClienteDtoAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ClienteOutputDTO toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteOutputDTO.class);
	}
	
	public List<ClienteOutputDTO> toCollectionModel (List<Cliente> clientes) {
		return clientes.stream()
				.map(cliente -> toModel(cliente))
				.collect(Collectors.toList());
	}
	
	public ClienteDetalhadoOutputDTO toModelForClienteDetalhado(Cliente cliente) {
		return modelMapper.map(cliente, ClienteDetalhadoOutputDTO.class);
	}
	
}
