package br.com.careplus.mev.adapter.in.controller.request.checkIn;

import br.com.careplus.mev.application.core.domain.enums.QualidadeSono;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCheckInSono(
        @NotNull Long idPaciente,
        @NotNull LocalDateTime dataRegistro,
        @NotNull @DecimalMin("0.0") @DecimalMax("24.0") Double horasDormidas,
        @NotNull QualidadeSono qualidade,
        @NotNull @Min(0) Integer despertares
) {
}
