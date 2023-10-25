package aplicacao.api.domain.response;

import aplicacao.api.domain.perfil.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
	
	    private String login;
	    private String email;
	    private String nome;
//	    private Perfil perfilUsuario;
//	    private String senha;

}
