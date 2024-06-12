package com.auomacaoISSFortaleza.demo.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auomacaoISSFortaleza.demo.api.model.output.UsuarioDetalhadoOutputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.UsuarioOutputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.Usuario;


@Component
public class UsuarioDtoAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioOutputDTO toModel(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioOutputDTO.class);
	}
	
	public List<UsuarioOutputDTO> toCollectionModel (List<Usuario> usuarios) {
		return usuarios.stream()
				.map(usuario -> toModel(usuario))
				.collect(Collectors.toList());
	}
	
	
	
	public UsuarioDetalhadoOutputDTO toModelforUsuarioDetalhado(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioDetalhadoOutputDTO.class);
	}
	
}
