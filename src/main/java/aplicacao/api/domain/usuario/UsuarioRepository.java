package aplicacao.api.domain.usuario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  , UsuarioCustomQuery{

    UserDetails findByLogin(String login);
    
    Optional<Usuario> findById(Long id);
    
    public Optional<Usuario> findByEmail(String email);
    
    @Query("SELECT d FROM Usuario d WHERE d.email = :email and d.id not in (:id)")
    public Optional<Usuario> findByEmailAndUserIdNot(String email,Long id);
    
    @Query("SELECT d FROM Usuario d WHERE d.login = :login")
    public Optional<Usuario> findOptByLogin(String login);
    
    @Query("SELECT d FROM Usuario d WHERE d.login = :login and d.id not in (:id)")
    public Optional<Usuario> findByLoginAndUserIdNot(String login , Long id);
    
    @Query("SELECT d FROM Usuario d WHERE d.email = :email")
    Page<Usuario> findByEmail(String email ,Pageable pageable );
    
    @Query("SELECT d FROM Usuario d WHERE d.nome = :nome")
    Page<Usuario> findByNome(String nome ,Pageable pageable );
    
    @Query("SELECT d FROM Usuario d WHERE d.nome = :email and d.nome = :nome")
    Page<Usuario> findByEmailAndNome(String email , String nome ,Pageable pageable );

	
    
    
    

}
