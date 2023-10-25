package aplicacao.api.domain.usuario.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aplicacao.api.domain.ValidacaoException;
import aplicacao.api.domain.request.UsuarioInput;
import aplicacao.api.domain.usuario.UsuarioRepository;

@Component
public class ValidacaoCadastro implements ValidadorCadastroUsuario{
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public void validar(UsuarioInput input) {
		if (!input.getSenha().equalsIgnoreCase(input.getConfirmeSenha())) {
			throw new ValidacaoException("Confirmação da Senha inválida");
		}

		if (repository.findByEmail(input.getEmail()).isPresent()) {
			throw new ValidacaoException("Já existe um usuário cadastrado com o email %s ".formatted(input.getEmail()));

		}

		if (repository.findOptByLogin(input.getLogin()).isPresent()) {
			throw new ValidacaoException("Já existe um usuário cadastrado com o login %s ".formatted(input.getLogin()));

		}
	}

}
