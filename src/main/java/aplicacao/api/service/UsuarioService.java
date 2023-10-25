package aplicacao.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import aplicacao.api.domain.ValidacaoException;
import aplicacao.api.domain.assembler.UsuarioAssembler;
import aplicacao.api.domain.request.AtualizarSenhaUsuarioInput;
import aplicacao.api.domain.request.FiltroRequest;
import aplicacao.api.domain.request.UsuarioAtualizacaoInput;
import aplicacao.api.domain.request.UsuarioInput;
import aplicacao.api.domain.response.UsuarioResponse;
import aplicacao.api.domain.usuario.Usuario;
import aplicacao.api.domain.usuario.UsuarioRepository;
import aplicacao.api.domain.usuario.validacoes.ValidadorAtualizacaoUsuario;
import aplicacao.api.domain.usuario.validacoes.ValidadorCadastroUsuario;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private UsuarioAssembler assembler;
	@Autowired
	private List<ValidadorCadastroUsuario> validadores;
	
	@Autowired
	private List<ValidadorAtualizacaoUsuario> validadoresParaAtualizacao;
	
	
	private ModelMapper modelMapper = new ModelMapper();

	public UsuarioResponse salvar(UsuarioInput usuarioInput) {
//		validacaoCadastro(usuarioInput);
		validadores.forEach(v -> v.validar(usuarioInput));
		Usuario usuario = assembler.toEntity(usuarioInput);
//		Usuario usuario = modelMapper.map(input, Usuario.class);
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioInput.getSenha()));
		repository.save(usuario);
		return modelMapper.map(usuario, UsuarioResponse.class);
	}
	
	
	
	
	
	

	public Page<UsuarioResponse> findAll(FiltroRequest filtro) {
		/*
		PageRequest pageable = PageRequest.of(filtro.getPagina(), filtro.getTamanho());
		Page<Usuario> pageuser = new PageImpl<>(new ArrayList<>());

		if (filtro.getEmail() != null && filtro.getNome() != null) {
			pageuser = repository.findByEmailAndNome(filtro.getEmail(), filtro.getNome(), pageable);
		} else if (filtro.getEmail() != null && filtro.getNome() == null) {
			pageuser = repository.findByEmail(filtro.getEmail(), pageable);
		} else if (filtro.getEmail() == null && filtro.getNome() != null) {
			pageuser = repository.findByNome(filtro.getNome(), pageable);
		} else {
			pageuser = repository.findAll(pageable);
		}

		List<UsuarioResponse> responses = pageuser.getContent().stream()
				.map(user -> modelMapper.map(user, UsuarioResponse.class)).collect(Collectors.toList());

		return new PageImpl<UsuarioResponse>(responses,
				PageRequest.of(pageuser.getPageable().getPageNumber(), pageuser.getSize()), pageuser.getTotalPages())
 */
		return repository.filtro(filtro);
	}
	
	
	
	
	
	public UsuarioResponse buscarPorId(Long id) {
        return assembler.toModel(buscarEValidar(id));
    }
	
	
	
	
	
	
	
	public UsuarioResponse atualizar(Long id, UsuarioAtualizacaoInput  usuarioInput) {
        Usuario usuario =   buscarEValidar(id);
        validadoresParaAtualizacao.forEach(v -> v.validar(id,usuarioInput));
		usuario = assembler.toEntity(usuario, usuarioInput);
		repository.save(usuario);
		return modelMapper.map(usuario, UsuarioResponse.class);
    }
	
	
	
	
	public UsuarioResponse alterarSenha(Long id, AtualizarSenhaUsuarioInput  usuarioInput) {
        Usuario usuario =   buscarEValidar(id);
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioInput.getSenha()));
		repository.save(usuario);
		return modelMapper.map(usuario, UsuarioResponse.class);   
	}
	
	
	
	
	
	
	 public void excluir(Long id) {
	        Usuario usuario = buscarEValidar(id);
	        repository.delete(usuario);
	 }
	
	 private Usuario buscarEValidar(Long id) {
	        return repository.findById(id).
	                orElseThrow(() -> new ValidacaoException("O Usúario  com o código " + id + " não foi encontrada !"));
	 }
	
}
