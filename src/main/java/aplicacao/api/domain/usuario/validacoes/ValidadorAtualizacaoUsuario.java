package aplicacao.api.domain.usuario.validacoes;

import aplicacao.api.domain.request.UsuarioAtualizacaoInput;
import aplicacao.api.domain.request.UsuarioInput;

public interface ValidadorAtualizacaoUsuario {
	
	void validar(Long usuarioId, UsuarioAtualizacaoInput usuarioInput);

}
