package com.auomacaoISSFortaleza.demo.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auomacaoISSFortaleza.demo.api.model.input.UsuarioInputDTO;
import com.auomacaoISSFortaleza.demo.domain.model.Usuario;


@Component
public class UsuarioDtoDesassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Usuario toDomainObject (UsuarioInputDTO usuarioInputDTO) {
		return modelMapper.map(usuarioInputDTO, Usuario.class);
	}

	public void copyToDomainObject(	UsuarioInputDTO usuarioInputDTO, Usuario usuario) {
		// se tiver que referenciar uma entidade em outra precisamos estanciar a entidade referenciada.
		// exemplo quero referenciar dentro da categoria um quiz (sentido faria se fosse ao contrário, mas é so pra fins didáticos)
		// categoria.setQuiz(new Cozinha())
		// se não fizermos isso, o JPA, vai achar que queremos trocar o ID da quiz já referenciada por outro a ID
		// enquanto o que queremos fazer é trocar a referencia do quiz na categoria.
		//permissao.setGrupoPermissoes(new HashSet<>());
		modelMapper.map(usuarioInputDTO, usuario);
	}
}
