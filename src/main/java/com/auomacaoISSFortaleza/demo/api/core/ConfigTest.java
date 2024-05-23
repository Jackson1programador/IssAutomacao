package com.auomacaoISSFortaleza.demo.api.core;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.auomacaoISSFortaleza.demo.domain.model.GrupoPermissao;
import com.auomacaoISSFortaleza.demo.domain.model.Permissao;
import com.auomacaoISSFortaleza.demo.domain.repository.GrupoPermissaoRepository;
import com.auomacaoISSFortaleza.demo.domain.repository.PermissaoRepository;

@Configuration
@Profile("teste")
public class ConfigTest implements CommandLineRunner{

	@Autowired
	private GrupoPermissaoRepository grupoPermissaoRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;


	@Override
	public void run(String... args) throws Exception {
		
		//adicionei grupo permissao no banco de dados para testes
		GrupoPermissao gp1 = new GrupoPermissao(null, "ADM", new HashSet<>());
		GrupoPermissao gp2 = new GrupoPermissao(null, "Cordenaodor", new HashSet<>());
		GrupoPermissao gp3 = new GrupoPermissao(null, "Executor", new HashSet<>());
		grupoPermissaoRepository.saveAll(Arrays.asList(gp1, gp2, gp3));
		
		//adicionei permissao no banco de dados para testes
		Permissao p1 = new Permissao(null,  "Cadastro Usuarios", "Essa permissao permite que possa criar e apagar Usuarios", new HashSet<>());
		Permissao p2 = new Permissao(null,  "Cadastro Empresa", "Essa permissao permite que possa criar, alterar e apagar empresas", new HashSet<>());
		Permissao p3 = new Permissao(null,  "MASTER", "Essa permissao permite que possa fazer tudo", new HashSet<>());
		//permissaoRepository.saveAll(Arrays.asList(p1, p2, p3));
		           
		//associar permissao com grupo Permissao no banco de dados para testes
		p1.getGrupoPermissoes().add(gp2);
		p2.getGrupoPermissoes().add(gp2);
		p3.getGrupoPermissoes().add(gp1);
		permissaoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
	}}
