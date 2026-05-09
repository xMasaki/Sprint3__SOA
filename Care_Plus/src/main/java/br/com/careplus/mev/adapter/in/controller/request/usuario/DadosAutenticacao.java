package br.com.careplus.mev.adapter.in.controller.request.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAutenticacao(
        @NotNull String login,
        @NotNull String senha
) {
}
