package br.com.careplus.mev.adapter.in.controller.request.checkIn;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCheckInSocial(
        @NotNull Long idPaciente,
        @NotNull LocalDateTime dataRegistro,
        @NotNull Boolean interacaoPositiva
) {
}
