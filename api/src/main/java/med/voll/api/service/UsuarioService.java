package med.voll.api.service;

import med.voll.api.dto.usuario.DadosCadastroUsuarioDTO;
import med.voll.api.entity.Usuario;
import med.voll.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioService (UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrarUsuario(DadosCadastroUsuarioDTO dadosUsuario) {
        var senhaCriptografada = passwordEncoder.encode(dadosUsuario.senha());
        return usuarioRepository.save(new Usuario(dadosUsuario, senhaCriptografada));
    }

}
