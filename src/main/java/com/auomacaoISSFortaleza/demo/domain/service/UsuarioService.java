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
import com.auomacaoISSFortaleza.demo.domain.model.Usuario;
import com.auomacaoISSFortaleza.demo.domain.repository.UsuarioRepository;


@Service
public class UsuarioService {

	private static final String MSG_USUARIO_EM_USO = "O usuario de código %d não pode ser removida, pois está em uso";
	private static final String MSG_USUARIO_NAO_ENCONTRADA = "Não existe o cadastro do usuario com esse código %d";
	
	@Autowired
	private UsuarioRepository UsuarioRepository;
	
	//@Autowired
	//private PermissaoService permissaoService;
	//
	
	public List<Usuario> list () {
		List<Usuario> list = UsuarioRepository.findAll();
		return list;
	}
	
	public Page<Usuario> listPage (Pageable pageable) {
		Page<Usuario> list = UsuarioRepository.findAll(pageable);
		return list;
	}
	
	public Optional<Usuario> buscaPorId (Long usuarioId) {
		Optional<Usuario> usuario = UsuarioRepository.findById(usuarioId);
		return usuario;
	}
	
	@Transactional
	public Usuario salvar (Usuario usuario) {
		return UsuarioRepository.save(usuario);
	}
	
	@Transactional
	public void excluir (Long usuarioId) {
		try {
			if (!UsuarioRepository.existsById(usuarioId)) {
				throw new ExceptionResourceNotFound(
						String.format(MSG_USUARIO_NAO_ENCONTRADA, usuarioId));
			}
			UsuarioRepository.deleteById(usuarioId);
			UsuarioRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new ExceptionEntidadeEmUso(
			String.format(MSG_USUARIO_EM_USO, usuarioId));
		}
	}
	
	//@Transactional
	//public void associarPermissaoAoPermissao (Long PermissaoId, Long permissaoId) {
	//	Permissao Permissao = buscarOuFalhar(PermissaoId);
	//	Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
	//	permissao.getPermissoes().add(Permissao);	
	//	permissaoService.salvar(permissao);
	//}
	
	
	//@Transactional
	//public void desassociarPermissaoAoPermissao (Long PermissaoId, Long permissaoId) {
	//	Permissao Permissao = buscarOuFalhar(PermissaoId);
	//	Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
	//	permissao.getPermissoes().remove(Permissao);	
	//	permissaoService.salvar(permissao);
	//}
	
	public Usuario buscarOuFalhar(Long usuarioId) {
		return buscaPorId(usuarioId).orElseThrow(() -> new ExceptionResourceNotFound(String.format(MSG_USUARIO_NAO_ENCONTRADA, usuarioId)));
	}
	
}
