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
import com.auomacaoISSFortaleza.demo.domain.repository.EmpresaRepository;


@Service
public class EmpresaService {

	private static final String MSG_EMPRESA_EM_USO = "A empresa de código %d não pode ser removida, pois está em uso";
	private static final String MSG_EMPRESA_NAO_ENCONTRADA = "Não existe o cadastro da empresa com esse código %d";
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	//@Autowired
	//private PermissaoService permissaoService;
	//
	
	public List<Empresa> list () {
		List<Empresa> list = empresaRepository.findAll();
		return list;
	}
	
	public Page<Empresa> listPage (Pageable pageable) {
		Page<Empresa> list = empresaRepository.findAll(pageable);
		return list;
	}
	
	public Optional<Empresa> buscaPorId (Long empresaId) {
		Optional<Empresa> Empresa = empresaRepository.findById(empresaId);
		return Empresa;
	}
	
	@Transactional
	public Empresa salvar (Empresa empresa) {
		return empresaRepository.save(empresa);
	}
	
	@Transactional
	public void excluir (Long empresaId) {
		try {
			if (!empresaRepository.existsById(empresaId)) {
				throw new ExceptionResourceNotFound(
						String.format(MSG_EMPRESA_NAO_ENCONTRADA, empresaId));
			}
			empresaRepository.deleteById(empresaId);
			empresaRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new ExceptionEntidadeEmUso(
			String.format(MSG_EMPRESA_EM_USO, empresaId));
		}
	}
	
	//@Transactional
	//public void associarPermissaoAoGrupoPermissao (Long grupoPermissaoId, Long permissaoId) {
	//	GrupoPermissao grupoPermissao = buscarOuFalhar(grupoPermissaoId);
	//	Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
	//	permissao.getGrupoPermissoes().add(grupoPermissao);	
	//	permissaoService.salvar(permissao);
	//}
	
	
	//@Transactional
	//public void desassociarPermissaoAoGrupoPermissao (Long grupoPermissaoId, Long permissaoId) {
	//	GrupoPermissao grupoPermissao = buscarOuFalhar(grupoPermissaoId);
	//	Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
	//	permissao.getGrupoPermissoes().remove(grupoPermissao);	
	//	permissaoService.salvar(permissao);
	//}
	
	public Empresa buscarOuFalhar(Long empresaId) {
		return buscaPorId(empresaId).orElseThrow(() -> new ExceptionResourceNotFound(String.format(MSG_EMPRESA_NAO_ENCONTRADA, empresaId)));
	}
	
}
