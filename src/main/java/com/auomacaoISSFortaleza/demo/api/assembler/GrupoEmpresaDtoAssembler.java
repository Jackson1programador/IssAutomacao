package com.auomacaoISSFortaleza.demo.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auomacaoISSFortaleza.demo.api.model.output.GrupoEmpresaDetalhadoOutputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.GrupoEmpresaOutputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.GrupoEmpresa;


@Component
public class GrupoEmpresaDtoAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public GrupoEmpresaOutputDTO toModel(GrupoEmpresa grupoEmpresa) {
		return modelMapper.map(grupoEmpresa, GrupoEmpresaOutputDTO.class);
	}
	
	public List<GrupoEmpresaOutputDTO> toCollectionModel (List<GrupoEmpresa> grupoEmpresas) {
		return grupoEmpresas.stream()
				.map(grupoEmpresa -> toModel(grupoEmpresa))
				.collect(Collectors.toList());
	}
	
	
	
	
	public GrupoEmpresaDetalhadoOutputDTO toModelForGrupoEmpresaDetalhado(GrupoEmpresa grupoEmpresa) {
		return modelMapper.map(grupoEmpresa, GrupoEmpresaDetalhadoOutputDTO.class);
	}
	
}
