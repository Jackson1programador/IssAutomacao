package com.auomacaoISSFortaleza.demo.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auomacaoISSFortaleza.demo.api.model.output.EmpresaDetalhadoOutputDTO;
import com.auomacaoISSFortaleza.demo.api.model.output.EmpresaOutputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.Empresa;


@Component
public class EmpresaDtoAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public EmpresaOutputDTO toModel(Empresa empresa) {
		return modelMapper.map(empresa, EmpresaOutputDTO.class);
	}
	
	public List<EmpresaOutputDTO> toCollectionModel (List<Empresa> empresas) {
		return empresas.stream()
				.map(empresa -> toModel(empresa))
				.collect(Collectors.toList());
	}
	
	
	
	public EmpresaDetalhadoOutputDTO toModelforEmpresaDetalhado(Empresa empresa) {
		return modelMapper.map(empresa, EmpresaDetalhadoOutputDTO.class);
	}
	
}
