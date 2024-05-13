package com.auomacaoISSFortaleza.demo.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auomacaoISSFortaleza.demo.api.model.input.PermissaoInputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.Permissao;


@Component
public class PermissaoDtoDesasembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Permissao toDomainObject (PermissaoInputDTO permissaoInputDTO) {
		return modelMapper.map(permissaoInputDTO, Permissao.class);
	}

	public void copyToDomainObject(	PermissaoInputDTO permissaoInputDTO, Permissao permissao) {
		// se tiver que referenciar uma entidade em outra precisamos estanciar a entidade referenciada.
		// exemplo quero referenciar dentro da categoria um quiz (sentido faria se fosse ao contrário, mas é so pra fins didáticos)
		// categoria.setQuiz(new Cozinha())
		// se não fizermos isso, o JPA, vai achar que queremos trocar o ID da quiz já referenciada por outro a ID
		// enquanto o que queremos fazer é trocar a referencia do quiz na categoria.
		modelMapper.map(permissaoInputDTO, permissao);
	}
}
