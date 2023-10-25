package aplicacao.api.domain.perfil;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "perfil")
@Entity
@Data
public class Perfil {
	
	
	@Id
	private Integer id;
	
	private String nome ;

}
