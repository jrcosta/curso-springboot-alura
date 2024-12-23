package med.voll.api.security.controller;

import jakarta.validation.Valid;
import med.voll.api.exception.InvalidCredentialsException;
import med.voll.api.security.entity.DadosAutenticacao;
import med.voll.api.security.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<DadosTokenJWT> autenticar(@RequestBody @Valid DadosAutenticacao dados) {
        try {
            var jwtToken = authService.autenticar(dados);
            return ResponseEntity.ok(new DadosTokenJWT(jwtToken));
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(401).body(new DadosTokenJWT(e.getMessage()));
        }
    }
}

