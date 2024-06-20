package com.auomacaoISSFortaleza.demo.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auomacaoISSFortaleza.demo.domain.exception.ExceptionEntidadeEmUso;
import com.auomacaoISSFortaleza.demo.domain.exception.ExceptionResourceNotFound;
import com.auomacaoISSFortaleza.demo.domain.model.Empresa;
import com.auomacaoISSFortaleza.demo.domain.model.GrupoEmpresa;
import com.auomacaoISSFortaleza.demo.domain.model.GrupoPermissao;
import com.auomacaoISSFortaleza.demo.domain.model.Permissao;
import com.auomacaoISSFortaleza.demo.domain.repository.GrupoEmpresaRepository;


@Service
public class GrupoEmpresaService {

	private static final String MSG_GRUPO_EMPRESA_EM_USO = "O Grupo da empresa de código %d não pode ser removida, pois está em uso";
	private static final String MSG_GRUPO_EMPRESA_NAO_ENCONTRADA = "Não existe o cadastro do grupo da empresa com esse código %d";
	
	@Autowired
	private GrupoEmpresaRepository grupoEmpresaRepository;
	
	@Autowired
	private EmpresaService empresaService;
	
	
	public List<GrupoEmpresa> list () {
		List<GrupoEmpresa> list = grupoEmpresaRepository.findAll();
		return list;
	}
	
	public Page<GrupoEmpresa> listPage (Pageable pageable) {
		Page<GrupoEmpresa> list = grupoEmpresaRepository.findAll(pageable);
		return list;
	}
	
	public Optional<GrupoEmpresa> buscaPorId (Long grupoEmpresaId) {
		Optional<GrupoEmpresa> grupoEmpresa = grupoEmpresaRepository.findById(grupoEmpresaId);
		return grupoEmpresa;
	}
	
	@Transactional
	public GrupoEmpresa salvar (GrupoEmpresa grupoEmpresa) {
		return grupoEmpresaRepository.save(grupoEmpresa);
	}
	
	@Transactional
	public void excluir (Long grupoEmpresaId) {
		try {
			if (!grupoEmpresaRepository.existsById(grupoEmpresaId)) {
				throw new ExceptionResourceNotFound(
						String.format(MSG_GRUPO_EMPRESA_NAO_ENCONTRADA, grupoEmpresaId));
			}
			grupoEmpresaRepository.deleteById(grupoEmpresaId);
			grupoEmpresaRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new ExceptionEntidadeEmUso(
			String.format(MSG_GRUPO_EMPRESA_EM_USO, grupoEmpresaId));
		}
	}
	
	@Transactional
	public void associarEmpresaAoGrupoEmpresa (Long grupoEmpresaId, Long empresaId) {
		GrupoEmpresa grupoEmpresa = buscarOuFalhar(grupoEmpresaId);
		Empresa empresa = empresaService.buscarOuFalhar(empresaId);
		grupoEmpresa.getEmpresas().add(empresa);
		empresa.setGrupoEmpresa(grupoEmpresa);
		empresaService.salvar(empresa);
	}
	
	
	@Transactional
	public void desassociarEmpresaAoGrupoEmpresa (Long grupoEmpresaId, Long empresaId) {
		GrupoEmpresa grupoEmpresa = buscarOuFalhar(grupoEmpresaId);
		Empresa empresa = empresaService.buscarOuFalhar(empresaId);
		//grupoEmpresa.getEmpresas().remove(empresa);
		empresa.setGrupoEmpresa(null);
	}
	
	public GrupoEmpresa buscarOuFalhar(Long grupoEmpresaId) {
		return buscaPorId(grupoEmpresaId).orElseThrow(() -> new ExceptionResourceNotFound(String.format(MSG_GRUPO_EMPRESA_NAO_ENCONTRADA, grupoEmpresaId)));
	}
	
}
