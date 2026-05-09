package br.com.careplus.mev.adapter.in.controller.request.checkIn;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCheckInSubstancias(
        @NotNull Long idPaciente,
        @NotNull LocalDateTime dataRegistro,
        @NotNull @DecimalMin("0.0") Double doses,
        @NotNull @Min(0) Integer cigarros
) {
}
