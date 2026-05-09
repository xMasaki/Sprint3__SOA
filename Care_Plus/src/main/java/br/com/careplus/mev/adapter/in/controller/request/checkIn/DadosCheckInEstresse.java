package br.com.careplus.mev.adapter.in.controller.request.checkIn;

import br.com.careplus.mev.application.core.domain.enums.NivelEstresse;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCheckInEstresse(
        @NotNull Long idPaciente,
        @NotNull LocalDateTime dataRegistro,
        @NotNull NivelEstresse nivelEstresse
) {
}
