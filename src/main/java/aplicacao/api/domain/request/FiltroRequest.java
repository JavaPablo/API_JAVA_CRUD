package aplicacao.api.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltroRequest {
	
	private String nome;
	
	private String email;
	
	private Integer pagina = 0 ;
	
	private Integer tamanho = 10;
	
}
