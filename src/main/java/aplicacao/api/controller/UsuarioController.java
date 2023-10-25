package aplicacao.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import aplicacao.api.domain.request.AtualizarSenhaUsuarioInput;
import aplicacao.api.domain.request.FiltroRequest;
import aplicacao.api.domain.request.UsuarioAtualizacaoInput;
import aplicacao.api.domain.request.UsuarioInput;
import aplicacao.api.domain.response.UsuarioResponse;
import aplicacao.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
	
	
	@Autowired
	private   UsuarioService service;

	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioResponse> cadastrar(@Valid @RequestBody UsuarioInput usuarioInput) {
		return ResponseEntity.ok(service.salvar(usuarioInput));
	}
	
	@GetMapping
	public Page<UsuarioResponse> listarTodos(FiltroRequest filtroRequest){
       	return service.findAll(filtroRequest);
	}
	
	@GetMapping("/{Id}")
    public ResponseEntity buscarPorId(@PathVariable Long Id) {
        return ResponseEntity.ok(service.buscarPorId(Id));
    }
	
	@PutMapping("/{Id}")
	public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long Id, @RequestBody @Valid UsuarioAtualizacaoInput usuarioInput) {
		return ResponseEntity.ok(service.atualizar(Id, usuarioInput));
	}
	
	@PutMapping("/alterar-senha/{Id}")
	public ResponseEntity alterarSenha(@PathVariable Long Id, @RequestBody @Valid AtualizarSenhaUsuarioInput usuarioInput) {
		service.alterarSenha(Id, usuarioInput);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{Id}")
	@Transactional
    public ResponseEntity deletar(@PathVariable Long Id) {
        service.excluir(Id);
        return ResponseEntity.noContent().build();
    }
	
	
	

}
 