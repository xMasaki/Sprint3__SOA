package br.com.careplus.mev.adapter.in.controller.request.usuario;

import br.com.careplus.mev.application.core.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(
        @NotBlank String login,
        @NotBlank String senha,
        @NotNull Role perfil
) {
}
