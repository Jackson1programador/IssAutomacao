package com.auomacaoISSFortaleza.demo.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auomacaoISSFortaleza.demo.api.model.output.PermissaoOutputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.Permissao;


@Component
public class PermissaoDtoAsembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public PermissaoOutputDTO toModel(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoOutputDTO.class);
	}
	
	public List<PermissaoOutputDTO> toCollectionModel (List<Permissao> permissoes) {
		return permissoes.stream()
				.map(permisaao -> toModel(permisaao))
				.collect(Collectors.toList());
	}
	
}
