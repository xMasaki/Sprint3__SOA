package br.com.careplus.mev.adapter.in.controller.request.medico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull Long id,
        String nome,
        String telefone
) {
}
