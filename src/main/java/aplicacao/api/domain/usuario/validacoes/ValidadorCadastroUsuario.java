package aplicacao.api.domain.usuario.validacoes;

import aplicacao.api.domain.request.UsuarioInput;

public interface ValidadorCadastroUsuario {
	
	void validar( UsuarioInput usuarioInput);

}
