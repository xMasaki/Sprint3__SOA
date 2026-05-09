package br.com.careplus.mev.adapter.in.controller.request.paciente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(
        @NotNull Long id,
        String nome,
        String telefone,
        Long medicoId
) {
}
