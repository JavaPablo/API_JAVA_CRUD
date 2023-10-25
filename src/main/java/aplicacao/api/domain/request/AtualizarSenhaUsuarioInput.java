package aplicacao.api.domain.request;

import aplicacao.api.domain.perfil.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AtualizarSenhaUsuarioInput {
	
	
	    @NotBlank
	    private String senha;
	    @NotBlank
	    private String confirmeSenha;
	    
//	    @NotBlank
//	    private Perfil perfil;
	      

}
