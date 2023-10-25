package aplicacao.api.domain.usuario;

import java.util.List;

import org.springframework.data.domain.Page;

import aplicacao.api.domain.request.FiltroRequest;
import aplicacao.api.domain.response.UsuarioResponse;

public interface UsuarioCustomQuery {
	
	public Page<UsuarioResponse> filtro(FiltroRequest filtroRequest);

}
