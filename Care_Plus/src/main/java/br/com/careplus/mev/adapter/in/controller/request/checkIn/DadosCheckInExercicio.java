package br.com.careplus.mev.adapter.in.controller.request.checkIn;

import br.com.careplus.mev.application.core.domain.enums.IntensidadeExercicio;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCheckInExercicio(
        @NotNull Long idPaciente,
        @NotNull LocalDateTime dataRegistro,
        @NotNull @Min(0) Integer sessoes,
        @NotNull IntensidadeExercicio intensidade,
        @NotNull @DecimalMin("0.0") Double duracaoTotal
) {
}
