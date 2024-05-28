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
import com.auomacaoISSFortaleza.demo.domain.model.GrupoUsuario;
import com.auomacaoISSFortaleza.demo.domain.repository.GrupoUsuarioRepository;


@Service
public class GrupoUsuarioService {

	private static final String MSG_GRUPO_USUARIO_EM_USO = "O Grupo do usuario de código %d não pode ser removida, pois está em uso";
	private static final String MSG_GRUPO_USUARIO_NAO_ENCONTRADA = "Não existe o cadastro do grupo do usuario com esse código %d";
	
	@Autowired
	private GrupoUsuarioRepository grupoUsuarioRepository;
	
	//@Autowired
	//private PermissaoService permissaoService;
	//
	
	public List<GrupoUsuario> list () {
		List<GrupoUsuario> list = grupoUsuarioRepository.findAll();
		return list;
	}
	
	public Page<GrupoUsuario> listPage (Pageable pageable) {
		Page<GrupoUsuario> list = grupoUsuarioRepository.findAll(pageable);
		return list;
	}
	
	public Optional<GrupoUsuario> buscaPorId (Long grupoUsuarioId) {
		Optional<GrupoUsuario> grupoUsuario = grupoUsuarioRepository.findById(grupoUsuarioId);
		return grupoUsuario;
	}
	
	@Transactional
	public GrupoUsuario salvar (GrupoUsuario grupoUsuario) {
		return grupoUsuarioRepository.save(grupoUsuario);
	}
	
	@Transactional
	public void excluir (Long grupoUsuarioId) {
		try {
			if (!grupoUsuarioRepository.existsById(grupoUsuarioId)) {
				throw new ExceptionResourceNotFound(
						String.format(MSG_GRUPO_USUARIO_NAO_ENCONTRADA, grupoUsuarioId));
			}
			grupoUsuarioRepository.deleteById(grupoUsuarioId);
			grupoUsuarioRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new ExceptionEntidadeEmUso(
			String.format(MSG_GRUPO_USUARIO_EM_USO, grupoUsuarioId));
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
	
	public GrupoUsuario buscarOuFalhar(Long GrupoUsuarioId) {
		return buscaPorId(GrupoUsuarioId).orElseThrow(() -> new ExceptionResourceNotFound(String.format(MSG_GRUPO_USUARIO_NAO_ENCONTRADA, GrupoUsuarioId)));
	}
	
}
