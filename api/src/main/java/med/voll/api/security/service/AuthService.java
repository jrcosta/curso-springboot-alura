package med.voll.api.security.service;

import med.voll.api.exception.InvalidCredentialsException;
import med.voll.api.repository.UsuarioRepository;
import med.voll.api.security.entity.DadosAutenticacao;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public String autenticar(DadosAutenticacao dados) {
        var usuario = usuarioRepository.findByLogin(dados.login());
        if (usuario == null || !passwordEncoder.matches(dados.senha(), usuario.getSenha())) {
            throw new InvalidCredentialsException("Usuário ou senha inválidos");
        }

        return tokenService.gerarToken(usuario);
    }
}

