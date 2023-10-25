package aplicacao.api.domain.usuario.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aplicacao.api.domain.ValidacaoException;
import aplicacao.api.domain.request.UsuarioAtualizacaoInput;
import aplicacao.api.domain.request.UsuarioInput;
import aplicacao.api.domain.usuario.UsuarioRepository;

@Component
public class ValidacaoAtualizacaoUsuario implements ValidadorAtualizacaoUsuario{
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public	void validar(Long usuarioId, UsuarioAtualizacaoInput usuarioInput) {
	

		if (repository.findByEmailAndUserIdNot(usuarioInput.getEmail(),usuarioId).isPresent()) {
			throw new ValidacaoException("Já existe um usuário cadastrado com o email %s ".formatted(usuarioInput.getEmail()));

		}

		if (repository.findByLoginAndUserIdNot(usuarioInput.getLogin() ,usuarioId).isPresent()) {
			throw new ValidacaoException("Já existe um usuário cadastrado com o login %s ".formatted(usuarioInput.getLogin()));

		}

	
	}

}
