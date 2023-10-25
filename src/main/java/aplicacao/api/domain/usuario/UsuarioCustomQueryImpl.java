package aplicacao.api.domain.usuario;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Pair;

import aplicacao.api.domain.assembler.UsuarioAssembler;
import aplicacao.api.domain.request.FiltroRequest;
import aplicacao.api.domain.response.UsuarioResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Parameter;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class UsuarioCustomQueryImpl implements UsuarioCustomQuery {

	@PersistenceContext
	EntityManager entityManager;
			
	@Autowired
	private UsuarioAssembler assembler;
			
	@Override
	public Page<UsuarioResponse> filtro(FiltroRequest filtroRequest) {

		return new PageImpl<>(assembler.toModelList(filtroUsuario( filtroRequest)),
				PageRequest.of(filtroRequest.getPagina(), filtroRequest.getTamanho()), filtroTotal(filtroRequest));
	}

	private List<Usuario> filtroUsuario(FiltroRequest filtroRequest) {
		Pair<String, HashMap<String, Object>> pairSqlAndParametros = buildSqlFiltro("SELECT u from Usuario u WHERE 1 = 1 ",
				filtroRequest);

		TypedQuery<Usuario> query = entityManager.createQuery(pairSqlAndParametros.getFirst(), Usuario.class);

		pairSqlAndParametros.getSecond().entrySet().forEach(param -> {
			query.setParameter(param.getKey(), param.getValue());
		});

		query.setMaxResults(filtroRequest.getTamanho());
		query.setFirstResult(filtroRequest.getPagina()  * filtroRequest.getTamanho());

		return query.getResultList();
	}

	private Integer filtroTotal(FiltroRequest filtroRequest) {
		Pair<String, HashMap<String, Object>> pairSqlAndParametros = buildSqlFiltro(
				"SELECT count(*) from Usuario u WHERE  1 = 1", filtroRequest);

		Query query = entityManager.createQuery(pairSqlAndParametros.getFirst());

		pairSqlAndParametros.getSecond().entrySet().forEach(param -> {
			query.setParameter(param.getKey(), param.getValue());
		});

	Long totalRow = (Long) 	query.getSingleResult();
		return totalRow.intValue();
	}
	
	
	

	public Pair<String, HashMap<String, Object>> buildSqlFiltro(String sqlBase, FiltroRequest filtroRequest) {
		StringBuilder sql = new StringBuilder(sqlBase);
		HashMap<String, Object> parametros = new HashMap<>();

		if (filtroRequest.getEmail() != null && !filtroRequest.getEmail().isBlank()) {
			sql.append(" and u.email = :email ");
			parametros.put("email", filtroRequest.getEmail());
		}

		if (filtroRequest.getNome() != null && !filtroRequest.getNome().isBlank()) {
			sql.append(" and u.nome = :nome ");
			parametros.put("nome", filtroRequest.getNome());
		}

		return Pair.of(sql.toString(), parametros);
	}

}
