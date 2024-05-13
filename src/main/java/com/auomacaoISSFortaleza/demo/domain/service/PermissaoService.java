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
import com.auomacaoISSFortaleza.demo.domain.model.Permissao;
import com.auomacaoISSFortaleza.demo.domain.repository.PermissaoRepository;


@Service
public class PermissaoService {

	private static final String MSG_PERMISSAO_EM_USO = "A permissão de código %d não pode ser removida, pois está em uso";
	private static final String MSG_PERMISSAO_NAO_ENCONTRADA = "Não existe o cadastro da permissão com esse código %d";
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public List<Permissao> list () {
		List<Permissao> list = permissaoRepository.findAll();
		return list;
	}
	
	public Page<Permissao> listPage (Pageable pageable) {
		Page<Permissao> list = permissaoRepository.findAll(pageable);
		return list;
	}
	
	public Optional<Permissao> buscaPorId (Long permissaoId) {
		Optional<Permissao> permissao = permissaoRepository.findById(permissaoId);
		return permissao;
	}
	
	@Transactional
	public Permissao salvar (Permissao permissao) {
		return permissaoRepository.save(permissao);
	}
	
	@Transactional
	public void excluir (Long permissaoId) {
		try {
			if (!permissaoRepository.existsById(permissaoId)) {
				throw new ExceptionResourceNotFound(
						String.format(MSG_PERMISSAO_NAO_ENCONTRADA, permissaoId));
			}
			permissaoRepository.deleteById(permissaoId);
			permissaoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new ExceptionEntidadeEmUso(
			String.format(MSG_PERMISSAO_EM_USO, permissaoId));
		}
	}
	
	public Permissao buscarOuFalhar(Long permissaoId) {
		return buscaPorId(permissaoId).orElseThrow(() -> new ExceptionResourceNotFound(String.format(MSG_PERMISSAO_NAO_ENCONTRADA, permissaoId)));
	}
	
}
