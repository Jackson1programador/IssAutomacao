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
import com.auomacaoISSFortaleza.demo.domain.model.GrupoPermissao;
import com.auomacaoISSFortaleza.demo.domain.model.Permissao;
import com.auomacaoISSFortaleza.demo.domain.repository.GrupoPermissaoRepository;


@Service
public class GrupoPermissaoService {

	private static final String MSG_PERMISSAO_EM_USO = "O Grupo da permissão de código %d não pode ser removida, pois está em uso";
	private static final String MSG_PERMISSAO_NAO_ENCONTRADA = "Não existe o cadastro do grupo da permissão com esse código %d";
	
	@Autowired
	private GrupoPermissaoRepository grupoPermissaoRepository;
	
	@Autowired
	private PermissaoService permissaoService;
	
	public List<GrupoPermissao> list () {
		List<GrupoPermissao> list = grupoPermissaoRepository.findAll();
		return list;
	}
	
	public Page<GrupoPermissao> listPage (Pageable pageable) {
		Page<GrupoPermissao> list = grupoPermissaoRepository.findAll(pageable);
		return list;
	}
	
	public Optional<GrupoPermissao> buscaPorId (Long grupoPermissaoId) {
		Optional<GrupoPermissao> grupoPermissao = grupoPermissaoRepository.findById(grupoPermissaoId);
		return grupoPermissao;
	}
	
	@Transactional
	public GrupoPermissao salvar (GrupoPermissao grupoPermissao) {
		return grupoPermissaoRepository.save(grupoPermissao);
	}
	
	@Transactional
	public void excluir (Long grupoPermissaoId) {
		try {
			if (!grupoPermissaoRepository.existsById(grupoPermissaoId)) {
				throw new ExceptionResourceNotFound(
						String.format(MSG_PERMISSAO_NAO_ENCONTRADA, grupoPermissaoId));
			}
			grupoPermissaoRepository.deleteById(grupoPermissaoId);
			grupoPermissaoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new ExceptionEntidadeEmUso(
			String.format(MSG_PERMISSAO_EM_USO, grupoPermissaoId));
		}
	}
	
	@Transactional
	public void associarPermissaoAoGrupoPermissao (Long grupoPermissaoId, Long permissaoId) {
		GrupoPermissao grupoPermissao = buscarOuFalhar(grupoPermissaoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
		permissao.getGrupoPermissoes().add(grupoPermissao);	
		permissaoService.salvar(permissao);
	}
	
	@Transactional
	public void desassociarPermissaoAoGrupoPermissao (Long grupoPermissaoId, Long permissaoId) {
		GrupoPermissao grupoPermissao = buscarOuFalhar(grupoPermissaoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
		permissao.getGrupoPermissoes().remove(grupoPermissao);	
		permissaoService.salvar(permissao);
	}
	
	public GrupoPermissao buscarOuFalhar(Long grupoPermissaoId) {
		return buscaPorId(grupoPermissaoId).orElseThrow(() -> new ExceptionResourceNotFound(String.format(MSG_PERMISSAO_NAO_ENCONTRADA, grupoPermissaoId)));
	}
	
}
