package aplicacao.api.domain.usuario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import aplicacao.api.domain.request.UsuarioInput;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UsuarioRepositoryTest {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private TestEntityManager em;

  
  @Test
  @DisplayName("Deve encontrar usuário quando ambos email e nome são fornecidos")
  void testFindByEmailAndNomeAmbosSaoFornecidos() {
      var usuario = cadastrarUsuario("Pablo", "pablo@gmail.com", "pablo", "123", "123");
      var usuarioEncontrado = usuarioRepository.findByEmailAndNome(usuario.getEmail(), usuario.getNome(), PageRequest.of(0, 5));
      assertNotNull(usuarioEncontrado);
      assertEquals(usuario.getEmail(), usuarioEncontrado.getContent().get(0).getEmail());
      assertEquals(usuario.getNome(), usuarioEncontrado.getContent().get(0).getNome());
  }

  @Test
  @DisplayName("Deve lançar exceção quando email não é fornecido")
  void testFindByEmailAndNomeQuandoEmailNaoEhFornecido() {
      assertThrows(IllegalArgumentException.class, () -> {
          usuarioRepository.findByEmailAndNome(null, "Pablo", PageRequest.of(0, 5));
      });
  }

  @Test
  @DisplayName("Deve lançar exceção quando nome não é fornecido")
  void testFindByEmailAndNomeQuandoNomeNaoEhFornecido() {
      assertThrows(IllegalArgumentException.class, () -> {
          usuarioRepository.findByEmailAndNome("pablo@gmail.com", null, PageRequest.of(0, 5));
      });
  }
  
  @Test
  @DisplayName("Deve encontrar usuário quando login e id são fornecidos")
  void findByLoginAndUserIdNotQuandoAmbosParametrosSaoFornecidos() {
      var usuario = cadastrarUsuario("Pablo", "pablo@gmail.com", "pablo", "123", "123");
      var usuarioEncontrado = usuarioRepository.findByLoginAndUserIdNot(usuario.getLogin(), usuario.getId());
      assertNotNull(usuarioEncontrado);
      assertEquals(usuario.getLogin(), usuarioEncontrado.get().getLogin());
      assertNotEquals(usuario.getId(), usuarioEncontrado.get().getId());
  }

  @Test
  @DisplayName("Deve lançar exceção quando login não é fornecido")
  void findByLoginAndUserIdNotQuandoLoginNaoEhFornecido() {
      assertThrows(IllegalArgumentException.class, () -> {
          usuarioRepository.findByLoginAndUserIdNot(null, 1L);
      });
  }

  @Test
  @DisplayName("Deve lançar exceção quando id não é fornecido")
  void findByLoginAndUserIdNotQuandoIdNaoEhFornecido() {
      assertThrows(IllegalArgumentException.class, () -> {
          usuarioRepository.findByLoginAndUserIdNot("pablo", null);
      });
  }
  
  @Test
  @DisplayName("Deve encontrar usuário quando email e id são fornecidos")
  void findByEmailAndUserIdNotQuandoAmbosParametrosSaoFornecidos() {
      var usuario = cadastrarUsuario("Pablo", "pablo@gmail.com", "pablo", "123", "123");
      var usuarioEncontrado = usuarioRepository.findByEmailAndUserIdNot(usuario.getEmail(), usuario.getId());
      assertNotNull(usuarioEncontrado);
      assertEquals(usuario.getEmail(), usuarioEncontrado.get().getEmail());
      assertNotEquals(usuario.getId(), usuarioEncontrado.get().getId());
  }

  @Test
  @DisplayName("Deve lançar exceção quando email não é fornecido")
  void findByEmailAndUserIdNotQuandoEmailNaoEhFornecido() {
      assertThrows(IllegalArgumentException.class, () -> {
          usuarioRepository.findByEmailAndUserIdNot(null, 1L);
      });
  }

  @Test
  @DisplayName("Deve lançar exceção quando id não é fornecido")
  void findByEmailAndUserIdNotQuandoIdNaoEhFornecido() {
      assertThrows(IllegalArgumentException.class, () -> {
          usuarioRepository.findByEmailAndUserIdNot("pablo@gmail.com", null);
      });
  }
  
  private Usuario cadastrarUsuario(String nome, String email, String login, String senha, String confirmeSenha) {
      var usuario = new Usuario();
      em.persist(usuario);
      return usuario;
  }

  private UsuarioInput dadosUsuario(String nome, String email, String login, String senha, String confirmeSenha) {
      return new UsuarioInput(
              nome,
              email,
              login,
              senha,
              confirmeSenha
      );
  }

}
