package com.auomacaoISSFortaleza.demo.api.core;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.auomacaoISSFortaleza.demo.domain.model.Cliente;
import com.auomacaoISSFortaleza.demo.domain.model.Empresa;
import com.auomacaoISSFortaleza.demo.domain.model.GrupoEmpresa;
import com.auomacaoISSFortaleza.demo.domain.model.GrupoPermissao;
import com.auomacaoISSFortaleza.demo.domain.model.GrupoUsuario;
import com.auomacaoISSFortaleza.demo.domain.model.Permissao;
import com.auomacaoISSFortaleza.demo.domain.model.Usuario;
import com.auomacaoISSFortaleza.demo.domain.repository.ClienteRepository;
import com.auomacaoISSFortaleza.demo.domain.repository.EmpresaRepository;
import com.auomacaoISSFortaleza.demo.domain.repository.GrupoEmpresaRepository;
import com.auomacaoISSFortaleza.demo.domain.repository.GrupoPermissaoRepository;
import com.auomacaoISSFortaleza.demo.domain.repository.GrupoUsuarioRepository;
import com.auomacaoISSFortaleza.demo.domain.repository.PermissaoRepository;
import com.auomacaoISSFortaleza.demo.domain.repository.UsuarioRepository;

@Configuration
@Profile("teste")
public class ConfigTest implements CommandLineRunner{

	@Autowired
	private GrupoPermissaoRepository grupoPermissaoRepository;
	
	@Autowired
	private GrupoUsuarioRepository grupoUsuarioRepository;
	
	@Autowired
	private GrupoEmpresaRepository grupoEmpresaRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;


	@Override
	public void run(String... args) throws Exception {
		
		//criando cliente
		Cliente c1 = new Cliente(null, "formma", "121231413424");
		Cliente c2 = new Cliente(null, "P&P", "121231413424");
		Cliente c3 = new Cliente(null, "Map", "121231413424");
		clienteRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		//adicionei grupo permissao no banco de dados para testes
		GrupoPermissao gp1 = new GrupoPermissao(null, "ADM", new HashSet<>(), new HashSet<>() );
		GrupoPermissao gp2 = new GrupoPermissao(null, "Cordenaodor", new HashSet<>(), new HashSet<>());
		GrupoPermissao gp3 = new GrupoPermissao(null, "Executor", new HashSet<>(), new HashSet<>());
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
		
		
		//adicionei grupo usuario no banco de dados para testes
		GrupoUsuario gu1 = new GrupoUsuario(null, "Coordenaodor jackson", new HashSet<>());
		GrupoUsuario gu2 = new GrupoUsuario(null, "gerente jackson", new HashSet<>());
		GrupoUsuario gu3 = new GrupoUsuario(null, "ADM Master jackson", new HashSet<>());
		grupoUsuarioRepository.saveAll(Arrays.asList(gu1, gu2, gu3));
		
		
		//adicionei grupo empresa no banco de dados para testes
		GrupoEmpresa ge1 = new GrupoEmpresa(null, "Coordenaodor jackson", new HashSet<>(), new HashSet<>());
		GrupoEmpresa ge2 = new GrupoEmpresa(null, "coordenador jack", new HashSet<>(), new HashSet<>());
		GrupoEmpresa ge3 = new GrupoEmpresa(null, "coordenador jackgol", new HashSet<>(), new HashSet<>());
		grupoEmpresaRepository.saveAll(Arrays.asList(ge1, ge2, ge3));
		
		//adicionei usuario no banco de dados para testes
		Usuario u1 = new Usuario(null, "Jackson", "jackson@gmail.com", "senha123");
		Usuario u2 = new Usuario(null, "Jackgol", "jackgol@gmail.com", "senha123");
		Usuario u3 = new Usuario(null, "Jack", "jack@gmail.com", "senha123");
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		//associar grupo de usuario a um usuario
		u1.setGrupoUsuario(gu1);
		u2.setGrupoUsuario(gu3);
		u3.setGrupoUsuario(gu1);
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3));
		gu1.getUsuarios().add(u1);
		gu2.getUsuarios().add(u3);
		gu3.getUsuarios().add(u2);
		grupoEmpresaRepository.saveAll(Arrays.asList(ge1, ge2, ge3));
		
		
		//associar grupo de permissoes a um usuario
		u1.setGrupoPermissao(gp1);
		u2.setGrupoPermissao(gp2);
		u3.setGrupoPermissao(gp3);
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3));
		gp1.getUsuarios().add(u1);
		gp2.getUsuarios().add(u2);
		gp3.getUsuarios().add(u3);
		grupoPermissaoRepository.saveAll(Arrays.asList(gp1, gp2, gp3));
		
		
		//associar grupo de empresa a um usuario
		u1.setGrupoEmpresa(ge1);
		u2.setGrupoEmpresa(ge2);
		u3.setGrupoEmpresa(ge3);
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3));
		ge1.getUsuarios().add(u1);
		ge2.getUsuarios().add(u2);
		ge3.getUsuarios().add(u3);
		grupoEmpresaRepository.saveAll(Arrays.asList(ge1, ge2, ge3));
		
		OffsetDateTime data1 = OffsetDateTime.now();
		OffsetDateTime data2 = OffsetDateTime.now();
		OffsetDateTime data3 = OffsetDateTime.now();
		
		//crianda entidade empresa
		Empresa e1 = new Empresa (null, "nome", "cnpj", "inscricao municpal", "cpfLogin", "senha iss fortaleza", null, null, data1,  true, true, true, true, true, true, true );
		Empresa e2 = new Empresa (null, "jackson", "123123123", "123", "123", "123", null, null, data2, true, true, true, true, true, true, true );
		Empresa e3 = new Empresa (null, "jack", "987987", "987", "987", "987", null, null, data3, true, true, true, true, true, true, true );
		empresaRepository.saveAll(Arrays.asList(e1, e2, e3));
		
		
		//associar grupo de empresa a um empresa
		e1.setGrupoEmpresa(ge1);
		e2.setGrupoEmpresa(ge2);
		e3.setGrupoEmpresa(ge3);
		empresaRepository.saveAll(Arrays.asList(e1, e2, e3));
		ge1.getEmpresas().add(e1);
		ge2.getEmpresas().add(e2);
		ge3.getEmpresas().add(e3);
		grupoEmpresaRepository.saveAll(Arrays.asList(ge1, ge2, ge3));
		
		
		//associar cliente a um usuario
		u1.setCliente(c1);
		u2.setCliente(c2);
		u3.setCliente(c3);
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3));
		c1.getUsuarios().add(u1);
		c2.getUsuarios().add(u2);
		c3.getUsuarios().add(u3);
		clienteRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		//associar cliente a uma empresa
		e1.setCliente(c1);
		e2.setCliente(c2);
		e3.setCliente(c3);
		empresaRepository.saveAll(Arrays.asList(e1, e2, e3));
		c1.getEmpresas().add(e1);
		c2.getEmpresas().add(e2);
		c3.getEmpresas().add(e3);
		clienteRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		
	}}
