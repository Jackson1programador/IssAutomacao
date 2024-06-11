package com.auomacaoISSFortaleza.demo.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auomacaoISSFortaleza.demo.api.model.output.GrupoPermissaoOutputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.GrupoPermissao;


@Component
public class GrupoPermissaoDtoAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public GrupoPermissaoOutputDTO toModel(GrupoPermissao grupoPermissao) {
		return modelMapper.map(grupoPermissao, GrupoPermissaoOutputDTO.class);
	}
	
	public List<GrupoPermissaoOutputDTO> toCollectionModel (List<GrupoPermissao> grupoPermissoes) {
		return grupoPermissoes.stream()
				.map(grupoPermisaao -> toModel(grupoPermisaao))
				.collect(Collectors.toList());
	}
	
}
