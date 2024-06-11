package com.auomacaoISSFortaleza.demo.api.assembler;

import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auomacaoISSFortaleza.demo.api.model.input.GrupoPermissaoInputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.GrupoPermissao;


@Component
public class GrupoPermissaoDtoDesassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public GrupoPermissao toDomainObject (GrupoPermissaoInputDTO grupoPermissaoInputDTO) {
		return modelMapper.map(grupoPermissaoInputDTO, GrupoPermissao.class);
	}

	public void copyToDomainObject(	GrupoPermissaoInputDTO grupoPermissaoInputDTO, GrupoPermissao grupoPermissao) {
		// se tiver que referenciar uma entidade em outra precisamos estanciar a entidade referenciada.
		// exemplo quero referenciar dentro da categoria um quiz (sentido faria se fosse ao contrário, mas é so pra fins didáticos)
		// categoria.setQuiz(new Cozinha())
		// se não fizermos isso, o JPA, vai achar que queremos trocar o ID da quiz já referenciada por outro a ID
		// enquanto o que queremos fazer é trocar a referencia do quiz na categoria.
		grupoPermissao.setPermissoes(new HashSet<>());	
		modelMapper.map(grupoPermissaoInputDTO, grupoPermissao);
	}
}
