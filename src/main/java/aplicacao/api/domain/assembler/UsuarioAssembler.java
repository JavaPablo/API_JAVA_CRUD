package aplicacao.api.domain.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import aplicacao.api.domain.request.UsuarioAtualizacaoInput;
import aplicacao.api.domain.request.UsuarioInput;
import aplicacao.api.domain.response.UsuarioResponse;
import aplicacao.api.domain.usuario.Usuario;


@Component
public class UsuarioAssembler {

	private ModelMapper mapper = new ModelMapper();

    public UsuarioResponse toModel(Usuario usuario) {
        return mapper.map(usuario, UsuarioResponse.class);
    }

    public List<UsuarioResponse> toModelList(List<Usuario> listUsuario) {
        return listUsuario.
                stream().map(usuario -> toModel(usuario)).collect(Collectors.toList());
    }

    public Usuario toEntity(UsuarioInput usuarioInput) {
        return mapper.map(usuarioInput, Usuario.class);
    }
    
    public Usuario toEntity(UsuarioAtualizacaoInput usuarioInput) {
        return mapper.map(usuarioInput, Usuario.class);
    }
    
    public Usuario toEntity(Usuario usuario , UsuarioAtualizacaoInput usuarioInput) {
      BeanUtils.copyProperties( usuarioInput,usuario,"senha","id");
     return  usuario ;
    }



}