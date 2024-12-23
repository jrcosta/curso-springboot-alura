package med.voll.api.dto.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuarioDTO(@NotNull String login, @NotNull String senha) {
}
