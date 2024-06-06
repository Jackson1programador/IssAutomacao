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
import com.auomacaoISSFortaleza.demo.domain.model.Cliente;
import com.auomacaoISSFortaleza.demo.domain.repository.ClienteRepository;


@Service
public class ClienteService {

	private static final String MSG_CLIENTE_EM_USO = "O cliente de código %d não pode ser removida, pois está em uso";
	private static final String MSG_CLIENTE_NAO_ENCONTRADA = "Não existe o cadastro do cliente com esse código %d";
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	//@Autowired
	//private PermissaoService permissaoService;
	//
	
	public List<Cliente> list () {
		List<Cliente> list = clienteRepository.findAll();
		return list;
	}
	
	public Page<Cliente> listPage (Pageable pageable) {
		Page<Cliente> list = clienteRepository.findAll(pageable);
		return list;
	}
	
	public Optional<Cliente> buscaPorId (Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		return cliente;
	}
	
	@Transactional
	public Cliente salvar (Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir (Long clienteId) {
		try {
			if (!clienteRepository.existsById(clienteId)) {
				throw new ExceptionResourceNotFound(
						String.format(MSG_CLIENTE_NAO_ENCONTRADA, clienteId));
			}
			clienteRepository.deleteById(clienteId);
			clienteRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new ExceptionEntidadeEmUso(
			String.format(MSG_CLIENTE_EM_USO, clienteId));
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
	
	public Cliente buscarOuFalhar(Long clienteId) {
		return buscaPorId(clienteId).orElseThrow(() -> new ExceptionResourceNotFound(String.format(MSG_CLIENTE_NAO_ENCONTRADA, clienteId)));
	}
	
}
