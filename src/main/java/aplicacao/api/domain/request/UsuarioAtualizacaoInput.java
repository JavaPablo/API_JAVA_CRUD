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
public class UsuarioAtualizacaoInput {
	
//		private Integer id;
		@NotBlank
		private String nome;
	    @NotBlank
	    private String login;
	    @NotBlank
	    private String email;	    
//	    @NotBlank
//	    private Perfil perfil;
	      

}
